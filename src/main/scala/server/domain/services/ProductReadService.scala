package server.domain.services

import server.domain.errors.DatabaseError
import server.model.Product
import zio.macros.accessible
import zio._

@accessible
trait ProductReadService {
  def getById(id: String): IO[DatabaseError, Option[Product]]
}
