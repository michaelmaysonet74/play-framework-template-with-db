//import play.sbt.PlayImport.PlayKeys._
import com.typesafe.sbt.packager.docker._

lazy val server = (project in file("."))
  .enablePlugins(PlayScala)
  .settings(
    name := """play-framework-template""",
    organization := "com.michaelmaysonet74",
    version := "1.2.0",
    scalaVersion := "2.13.8",
    libraryDependencies ++= {
      val macwireVersion = "2.5.7"
      val playWsStandaloneVersion = "2.1.10"
      val slickVersion = "3.3.3"

      Seq(
        "org.scalatestplus.play" %% "scalatestplus-play" % "5.0.0" % Test,
        "com.softwaremill.macwire" %% "macros" % macwireVersion % Provided,
        "com.softwaremill.macwire" %% "util" % macwireVersion,
        "com.typesafe.play" %% "play-ahc-ws-standalone" % playWsStandaloneVersion,
        "com.typesafe.play" %% "play-ws-standalone-json" % playWsStandaloneVersion,
        "com.typesafe.slick" %% "slick" % slickVersion,
        "com.typesafe.slick" %% "slick-hikaricp" % slickVersion,
        "org.postgresql" % "postgresql" % "42.3.4"
      )
    }
  )
  .settings(
    packageName := "play-framework-template",
    dockerExposedPorts ++= Seq(9000),
    dockerChmodType := DockerChmodType.UserGroupWriteExecute,
    dockerPermissionStrategy := DockerPermissionStrategy.CopyChown,
    dockerEnvVars := Map(
      "APPLICATION_SECRET" -> sys.env.getOrElse("APPLICATION_SECRET", ""),
      "POSTGRES_USER" -> sys.env.getOrElse("POSTGRES_USER", ""),
      "POSTGRES_PASSWORD" -> sys.env.getOrElse("POSTGRES_PASSWORD", "")
    )
  )
// .settings(
//   playDefaultPort := 9000
// )
