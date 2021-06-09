package server.application

import server.model._
import zio._
import zio.logging.Logger

trait UserService {

  def find(id: UserId): UIO[Option[User]]

  def get(): UIO[List[User]]

  def create(name: Username, age: Age): UIO[Unit]
}

object UserService {

  private val initUsers                                            = List(
    User(UserId.fromInt(0).get, Username.fromString("John").get, Age.fromInt(42).get),
    User(UserId.fromInt(1).get, Username.fromString("Jane").get, Age.fromInt(37).get)
  ).map(u => u.id -> u).toMap

  val live: ZLayer[Has[Logger[String]], Nothing, Has[UserService]] =
    ZLayer.fromEffect(
      for {
        log   <- ZIO.service[Logger[String]]
        users <- Ref.make(initUsers)
      } yield new UserService {

        def find(id: UserId) = log.debug(s"Find user $id") *> users.get.map(_.get(id))
        def get()            = log.debug("Get all users") *> users.get.map(_.values.toList.sortBy(_.id.value))

        def create(name: Username, age: Age) =
          log.debug(s"Create new user") *> users.modify { db =>
            val nextId  = UserId.fromInt(db.keySet.map(_.value).max + 1).get
            val newUser = User(nextId, name, age)
            () -> db.updated(nextId, newUser)
          }
      }
    )
}
