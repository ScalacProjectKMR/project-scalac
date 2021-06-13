package server.domain.repositories

import server.domain.errors.DatabaseError
import server.model.Product
import server.domain.errors.ServerError
import zio.macros.accessible
import zio._

@accessible
trait ProductRepository {
  def insert(entity: Product): IO[DatabaseError, String]

  def update(entity: Product): IO[DatabaseError, Int]

}
