package server.domain.services

import zio._
import server.domain.errors.DatabaseError
import slick.dbio.DBIO
import slick.interop.zio.DatabaseProvider
import slick.interop.zio.syntax._

trait ShopDatabase {
  def toZIO[A](action: DBIO[A]): IO[DatabaseError, A]
}

object ShopDatabase {

  val live: URLayer[Has[DatabaseProvider], Has[ShopDatabase]] = ZLayer.fromFunction { env =>
    new ShopDatabase {
      override def toZIO[A](action: DBIO[A]) = ZIO.fromDBIO(action).mapError(DatabaseError).provide(env)
    }
  }
}
