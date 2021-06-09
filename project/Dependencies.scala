import sbt._

object Dependencies {
  lazy val scalaTest = "org.scalatest" %% "scalatest" % "3.2.8"
  lazy val akkaActorTyped = "com.typesafe.akka" %% "akka-actor-typed" % "2.6.8"
  lazy val akkaStream =  "com.typesafe.akka" %% "akka-stream" % "2.6.8"
  lazy val akkaHttp = "com.typesafe.akka" %% "akka-http" % "10.2.4"
  lazy val zio = "dev.zio" %% "zio" % "1.0.9"
  lazy val zioStreams = "dev.zio" %% "zio-streams" % "1.0.9"
  lazy val zioConfig = "dev.zio" %% "zio-config" % "1.0.6"
  lazy val zioConfigMagnolia = "dev.zio" %% "zio-config-magnolia" % "1.0.6"
  lazy val slick = "com.typesafe.slick" %% "slick" % "3.3.3"
  lazy val zioAkkaInterop = "io.scalac" %% "zio-akka-http-interop" % "0.4.0"
  lazy val zioMagic ="io.github.kitlangton" %% "zio-magic" % "0.3.2"
  lazy val zioLogging = "dev.zio" %% "zio-logging" % "0.5.10"
  lazy val circeCore = "io.circe" %% "circe-core" % "0.14.1"
  lazy val circeGeneric = "io.circe" %% "circe-generic" % "0.14.1"
  lazy val circeParser = "io.circe" %% "circe-parser" % "0.14.1"
  lazy val akkaHttpCirce = "de.heikoseeberger" %% "akka-http-circe" % "1.36.0"
  lazy val zioConfigTypesafe = "dev.zio" %% "zio-config-typesafe" % "1.0.6"
}
