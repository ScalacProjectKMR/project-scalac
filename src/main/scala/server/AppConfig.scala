package server

import akka.http.interop.HttpServer
import zio.config.ConfigDescriptor
import zio.config.typesafe._
import zio.config.magnolia.DeriveConfigDescriptor.descriptor
import zio.{ Has, Layer }

case class AppConfig(
  name: String,
  http: HttpServer.Config
)

/**
 * ZIO Config is a library for loading configuration files, similar to PureConfig, however
 * built ontop of ZIO, so it seamlessly integrates with ZIO-based applications.
 *
 * Using Magnolia we can derrive the config descriptor from a case class.
 */
object AppConfig {

  val configuration: ConfigDescriptor[AppConfig] =
    descriptor[AppConfig]

  val live: Layer[Throwable, Has[AppConfig]] =
    TypesafeConfig.fromDefaultLoader(configuration)
}
