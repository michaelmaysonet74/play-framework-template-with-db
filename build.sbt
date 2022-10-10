import play.sbt.PlayImport.PlayKeys._
import com.typesafe.sbt.packager.docker._

lazy val server = (project in file("."))
  .enablePlugins(PlayScala)
  .settings(
    name := """play-framework-template-with-db""",
    organization := "com.michaelmaysonet74",
    version := "2.1.0",
    scalaVersion := "2.13.8",
    libraryDependencies ++= {
      val macwireVersion = "2.5.8"
      val playSlickVersion = "5.1.0"
      Seq(
        "org.scalatestplus.play" %% "scalatestplus-play" % "5.1.0" % Test,
        "com.softwaremill.macwire" %% "macros" % macwireVersion % Provided,
        "com.softwaremill.macwire" %% "util" % macwireVersion,
        "com.typesafe.play" %% "play-slick" % playSlickVersion,
        "com.typesafe.play" %% "play-slick-evolutions" % playSlickVersion,
        "org.postgresql" % "postgresql" % "42.5.0",
        ws
      )
    },
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
