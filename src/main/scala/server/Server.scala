package server

import akka.actor.ActorSystem
import akka.http.interop.HttpServer
import akka.http.scaladsl.server.Route
import server.application.{ EchoService, SystemService, UserService }
import zio.{ App, ExitCode, URIO, ZEnv, ZIO, ZLayer, ZManaged }
import zio.config.syntax._
import zio.logging._
import zio.magic._

object Server extends App {

  def run(args: List[String]): URIO[ZEnv, ExitCode] =
    program.exitCode.injectSome[ZEnv](
      HttpServer.live,
      actorSystem.orDie,
      httpServerConfig.orDie,
      AppConfig.live.orDie,
      routes,
      Api.live,
      EchoService.live,
      UserService.live,
      SystemService.live,
      logger
    )

  private lazy val program =
    HttpServer.start.tapM(_ => log.info("Server running...")).useForever

  private lazy val actorSystem =
    ZLayer.fromServiceManaged { config: AppConfig =>
      val create   = ZIO.effect(ActorSystem(config.name))
      val tearDown = (s: ActorSystem) => log.info("Shutting down...") *> ZIO.fromFuture(_ => s.terminate()).ignore

      ZManaged.make(create)(tearDown)
    }

  private lazy val httpServerConfig =
    AppConfig.live.narrow(_.http)

  private lazy val routes =
    ZLayer.fromService[Api, Route](_.routes)

  private lazy val logger =
    Logging.console(
      logLevel = LogLevel.Debug,
      format = LogFormat.ColoredLogFormat()
    ) >>> Logging.withRootLoggerName("05-logging")
}
