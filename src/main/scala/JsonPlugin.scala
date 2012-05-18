package com.feth.play.plugin.jsonminify

import sbt._
import sbt.Keys._
import PlayProject._

// initially based on https://github.com/pufuwozu/ray
// and https://raw.github.com/knuton/play-stylus
object JsonPlugin extends Plugin {
  val id = "play-jsonminify"
  val jsonminifyEntryPoints = SettingKey[PathFinder](id+"-entry-points")
  val jsonminifyOptions = SettingKey[Seq[String]](id+"-options")
  
  val JsonWatcher = PlayProject.AssetsCompiler(id,
    (_ ** "*.json"),
    jsonminifyEntryPoints in Compile,
    { (name, min) => name.replace(".json", if (min) ".min.json" else ".json") },
    { JsonCompiler.compile _ },
    jsonminifyOptions in Compile
  )

  override val settings = Seq(
    jsonminifyEntryPoints <<= (sourceDirectory in Compile).apply(base => ((base / "assets" ** "*.json") --- base / "assets" ** "_*")),
    jsonminifyOptions := Seq.empty[String],
    resourceGenerators in Compile <+= JsonWatcher
  )

}
