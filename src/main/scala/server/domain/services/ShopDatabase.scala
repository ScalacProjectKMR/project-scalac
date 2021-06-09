package server.domain.services

import zio._
import server.domain.errors.DatabaseError
import slick.dbio.DBIO
import slick.interop.zio.DatabaseProvider
import slick.interop.zio.syntax._
import server.ShopDatabase

object ShopDatabase {

  trait Service {
    def toZIO[A](action: DBIO[A]): IO[DatabaseError, A]
  }

  val live: URLayer[Has[DatabaseProvider], ShopDatabase] = ZLayer.fromFunction { env =>
    new Service {
      override def toZIO[A](action: DBIO[A]) = ZIO.fromDBIO(action).mapError(DatabaseError).provide(env)
    }
  }
}
