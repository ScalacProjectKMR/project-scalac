package server.domain.services

import zio.macros.accessible
import zio.IO
import server.domain.errors.DatabaseError

@accessible
object ProductReadService {

  trait Service {

    def getProductById(id: String): IO[DatabaseError, Int]
  }
}
