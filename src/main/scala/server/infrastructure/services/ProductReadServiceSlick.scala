package server.infrastructure.services

import server.domain.errors.DatabaseError
import server.domain.services.ProductReadService

import server._
import zio._

object ProductReadServiceSlick {

  val live: URLayer[ShopDatabase, ProductReadService] = ZLayer.fromService { database =>
    new ProductReadService.Service {
      import slick.jdbc.PostgresProfile.api._
      override def getProductById(id: String): IO[DatabaseError, Int] = {
        val product = ZIO.succeed(0)
        product
      }
    }
  }
}
