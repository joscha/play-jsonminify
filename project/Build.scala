import sbt._
import Keys._

object PluginBuild extends Build {
  val playPath = Option(System.getProperty("play.path")).getOrElse("../play-2.0.1")
  val playVersion = Option(System.getProperty("play.version")).getOrElse("2.0.1")

  val playRepository = Resolver.file("Local Play Repository", file(new File(playPath, "repository/local").getPath))(Resolver.ivyStylePatterns)

  val typesafeRepository = Resolver.url("Typesafe repository", url("http://repo.typesafe.com/typesafe/releases/"))(Resolver.ivyStylePatterns)
  val projectId = "play-jsonminify"
  lazy val root = Project(id = projectId, base = file("."))
}
