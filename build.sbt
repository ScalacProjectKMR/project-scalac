import Dependencies._

ThisBuild / scalaVersion := "2.13.5"
ThisBuild / version := "0.1.0-SNAPSHOT"
ThisBuild / organization := "com.example"
ThisBuild / organizationName := "example"

lazy val root = (project in file("."))
  .settings(
    name := "project-scalac",
    libraryDependencies += scalaTest % Test,
    libraryDependencies += akkaActorTyped,
    libraryDependencies += akkaStream,
    libraryDependencies += akkaHttp,
    libraryDependencies += zio,
    libraryDependencies += zioStreams,
    libraryDependencies += zioConfig,
    libraryDependencies += zioConfigMagnolia,
    libraryDependencies += slick,
    libraryDependencies += zioSlickInterop,
    libraryDependencies += zioAkkaInterop,
    libraryDependencies += zioMagic,
    libraryDependencies += zioLogging,
    libraryDependencies += circeCore,
    libraryDependencies += circeGeneric,
    libraryDependencies += circeParser,
    libraryDependencies += akkaHttpCirce,
    libraryDependencies += zioConfigTypesafe
  )

// See https://www.scala-sbt.org/1.x/docs/Using-Sonatype.html for instructions on how to publish to Sonatype.
