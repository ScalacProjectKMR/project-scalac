package server.application

import server.AppConfig
import server.model.dto.SystemInfoResponse
import zio._
import zio.clock.Clock
import zio.logging.Logger

import java.time.format.DateTimeFormatter

trait SystemService {
  def info(): ZIO[Any, Nothing, SystemInfoResponse]
}

object SystemService {

  val live: ZLayer[Has[AppConfig] with Has[Clock.Service] with Has[Logger[String]], Nothing, Has[SystemService]] =
    ZLayer.fromServices[AppConfig, Clock.Service, Logger[String], SystemService] { (config, clock, log) => () =>
      for {
        _    <- log.debug("Query system info")
        time <- clock.currentDateTime.orDie
      } yield SystemInfoResponse(
        config.name,
        time.format(DateTimeFormatter.RFC_1123_DATE_TIME)
      )
    }
}
