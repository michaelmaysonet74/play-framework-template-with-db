import Dependencies._
import play.sbt.PlayImport.PlayKeys._
import com.typesafe.sbt.packager.docker._

ThisBuild / scalaVersion := "2.13.8"
ThisBuild / version := "2.2.0"
ThisBuild / organization := "com.michaelmaysonet74"
ThisBuild / organizationName := "michaelmaysonet74"

lazy val root = (project in file("."))
  .enablePlugins(PlayScala)
  .settings(
    name := """play-framework-template-with-db""",
    libraryDependencies ++= Seq(
      scalaTest % Test,
      macwireMacros % Provided,
      macwireUtil,
      playSlick,
      playSlickEvolutions,
      postgres,
      ws
    ),
    playDefaultPort := 9000
  )
  .settings(
    packageName := "play-framework-template-with-db",
    dockerExposedPorts ++= Seq(9000),
    dockerChmodType := DockerChmodType.UserGroupWriteExecute,
    dockerPermissionStrategy := DockerPermissionStrategy.CopyChown,
    dockerEnvVars := Map(
      "APPLICATION_SECRET" -> sys.env.getOrElse("APPLICATION_SECRET", ""),
      "POSTGRES_USER" -> sys.env.getOrElse("POSTGRES_USER", ""),
      "POSTGRES_PASSWORD" -> sys.env.getOrElse("POSTGRES_PASSWORD", "")
    )
  )
