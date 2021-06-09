package server

import akka.http.interop.HttpServer
import zio.config.ConfigDescriptor
import zio.config.typesafe._
import zio.config.magnolia.DeriveConfigDescriptor.descriptor
import zio.{Has, Layer}
import com.typesafe.config.Config

case class AppConfig(
  name: String,
  http: HttpServer.Config
)

object AppConfig {

  val configuration: ConfigDescriptor[AppConfig] =
    descriptor[AppConfig]

  val live: Layer[Throwable, Has[AppConfig]] =
    TypesafeConfig.fromDefaultLoader(configuration)
}
