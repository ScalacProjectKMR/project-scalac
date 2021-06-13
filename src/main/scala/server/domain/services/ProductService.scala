package server.domain.services

import zio.macros.accessible
import server.domain.errors.DatabaseError
import server.model.Product
import server.domain.errors.ServerError
import zio._
import server.domain.repositories._
import server.domain.services._
import server.application._

trait ProductService {
  def findById(id: String): IO[DatabaseError, Product]

  def insert(entity: Product): IO[ServerError, String]

  def update(entity: Product): IO[ServerError, Int]
}

object ProductService {

  val live: URLayer[Has[ProductRepository] with Has[ProductReadService], Has[ProductService]] = ZLayer.fromFunction {
    env =>
      new ProductServiceLive(env.get[ProductRepository], env.get[ProductReadService])
  }
}
