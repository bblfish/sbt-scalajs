
//import com.typesafe.sbt.web.Import.WebKeys

import sbt._
import Keys._
 
import com.typesafe.sbt.web.SbtWeb
import com.typesafe.sbt.web.Import._
import com.typesafe.sbt.web.Import.WebKeys._
import com.inthenow.sbt.scalajs._

object ThisBuild extends Build {
  val Version = "0.1"
   val logger = ConsoleLogger()

  
lazy val prj  = Project(
    id = "prj",
    base = file("."),
    settings = defaultSettings   ++Seq(
     // WebKeys.importDirectly := true,
     // WebKeys.importDirectly in TestAssets := true
    )
  ).enablePlugins(SbtWeb) 
   

  lazy val defaultSettings: Seq[Setting[_]] = Seq(//Defaults.defaultSettings ++ Seq(
    //organization := "com.typesafe.play",
    organization := "default",
    version := Version,
    scalaVersion := "2.11.2",
    publishMavenStyle := false
  )

 
}