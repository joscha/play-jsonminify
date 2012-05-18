package com.feth.play.plugin.jsonminify

import sbt.PlayExceptions.AssetCompilationException
import org.json.simple.JSONValue
import org.json.simple.parser.JSONParser
import org.json.simple.parser.ParseException
import scala.io.Source

import java.io.File

object StylusCompiler {

  def compile(file: File, options: Seq[String]): (String, Option[String], Seq[File]) = {
      val data = Source.fromFile(file) mkString

      var pretty: String = null
      var minified: String = null

      try {
        val p = new JSONParser()
        val o = p.parse(data);
        minified = o.toString()
        val w = new IndentationWriter()

        JSONValue.writeJSONString(o, w);
        pretty = w.getBuffer().toString()
      } catch {
        case pe: ParseException => {
          val lines = data.substring(0, pe.getPosition()).split("\\r?\\n")
          val line = lines.length
          val pos = lines(line - 1).length
          throw AssetCompilationException(Some(file),
            "JSON minifier: "+pe.toString() + " (counted from begin of file)",
            line,
            pos)
        }
        case e: Exception => {
          throw AssetCompilationException(Some(file),
            e.getMessage(),
            0,
            0)
        }
      }

      (pretty, Option(minified), Seq(file))
  }
}