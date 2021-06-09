package server.application

import zio._
import zio.logging.Logger

trait EchoService {
  def ping(data: String): ZIO[Any, Nothing, String]
}

object EchoService {

  val live: ZLayer[Has[Logger[String]], Nothing, Has[EchoService]] =
    ZLayer.fromService(log => data => log.debug(s"Got $data") *> ZIO.succeed(data))
}
