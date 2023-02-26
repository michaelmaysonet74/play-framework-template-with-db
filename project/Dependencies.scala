import sbt._

object Dependencies {

  private val macwireVersion = "2.5.8"
  private val playSlickVersion = "5.1.0"

  lazy val scalaTest = "org.scalatestplus.play" %% "scalatestplus-play" % "5.1.0"
  lazy val macwireMacros = "com.softwaremill.macwire" %% "macros" % macwireVersion
  lazy val macwireUtil = "com.softwaremill.macwire" %% "util" % macwireVersion
  lazy val playSlick = "com.typesafe.play" %% "play-slick" % playSlickVersion
  lazy val playSlickEvolutions = "com.typesafe.play" %% "play-slick-evolutions" % playSlickVersion
  lazy val postgres = "org.postgresql" % "postgresql" % "42.5.0"

}
