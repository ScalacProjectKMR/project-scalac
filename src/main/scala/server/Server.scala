package server

import akka.actor.ActorSystem
import akka.http.interop.HttpServer
import akka.http.scaladsl.server.Route
import zio.{App, ExitCode, URIO, ZEnv, ZIO, ZLayer, ZManaged}
import zio.config.syntax._
import zio.logging._
import zio.magic._
import slick.jdbc.PostgresProfile
import slick.interop.zio.DatabaseProvider
import server.domain.services.ShopDatabase
import com.typesafe.config.{Config, ConfigFactory}

object Server extends App {

  def run(args: List[String]): URIO[ZEnv, ExitCode] =
    program.exitCode.injectSome[ZEnv](
      HttpServer.live,
      actorSystem.orDie,
      databaseConfig.orDie,
      databaseBackendLayer,
      DatabaseProvider.live.orDie,
      ShopDatabase.live,
      httpServerConfig.orDie,
      AppConfig.live.orDie,
      routes,
      Api.live,
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

  private val databaseConfig =
    ZLayer.fromEffect(ZIO(ConfigFactory.load.resolve).map(_.getConfig("database")))

  private val databaseBackendLayer = ZLayer.succeed(PostgresProfile.backend)

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
