name := """sassy-jams"""
organization := "com.rbmh"

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayScala)

scalaVersion := "2.12.6"

libraryDependencies ++= Seq(
  guice,
  "org.scalatestplus.play" %% "scalatestplus-play" % "3.1.2" % Test,
  "org.scalaj" %% "scalaj-http" % "2.4.1",
  "com.itv" %% "scalapact-circe-0-9" % "2.3.2" % Test,
  "com.itv" %% "scalapact-http4s-0-18" % "2.3.2" % Test,
  "com.itv" %% "scalapact-scalatest" % "2.3.2" % Test,
  "org.scalatest" %% "scalatest" % "3.0.5" % Test,
  "org.slf4j" % "slf4j-simple" % "1.7.25" % Test
)