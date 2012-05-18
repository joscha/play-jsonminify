sbtPlugin := true

name := projectId

organization := "com.feth"

version := "0.1.0"

description := "SBT plugin for handling JSON minification in Play 2.x"

resolvers += playRepository

resolvers += typesafeRepository

addSbtPlugin("play" % "sbt-plugin" % playVersion)

libraryDependencies += "com.googlecode.json-simple" % "json-simple" % "1.1.1"

publishTo := Some(playRepository)

publishMavenStyle := false


//for upload to github

//publishTo := Some(Resolver.file("Github Pages", Path.userHome / "github.com" / "repo" asFile))

//publishMavenStyle := true