package com.inthenow.sbt.scalajs

import sbt._
import sbt.Keys._

object SbtScalajs extends AutoPlugin {

  override def requires = sbt.plugins.JvmPlugin

  import scala.scalajs.sbtplugin.ScalaJSPlugin.ScalaJSKeys._
  import scala.scalajs.sbtplugin.ScalaJSPlugin._

  val preScalaJSSettings = {
    Seq(
      (crossTarget in fastOptJS) in Compile := (crossTarget in Compile).value / "scalajs_managed" / "js",
      (crossTarget in fastOptJS) in Test := (crossTarget in Test).value / "scalajs_managed" / "js"
    )
  } ++ scalaJSSettings

  val postScalaJSSettings = {
    Seq(
      ScalaJSKeys.fastOptJS in Compile <<= (ScalaJSKeys.fastOptJS in Compile) dependsOn (compile in Compile) triggeredBy (compile in Compile),
      ScalaJSKeys.fastOptJS in Test <<= (ScalaJSKeys.fastOptJS in Test) dependsOn (fastOptJS in Compile) triggeredBy (compile in Test)
    )
  }

  override def projectSettings =
    preScalaJSSettings ++ postScalaJSSettings ++
      Seq(
      )

  val noRootSettings = Seq(
    publish := {},
    publishLocal := {}
  )

  def shareDirectories(that: Project, dir: String ) = Seq(
    // pseudo link shared files from jvm source/resource directories to js
    unmanagedSourceDirectories   in Compile += (baseDirectory in that).value / "src/main/scala" / dir,
    unmanagedSourceDirectories   in Test    += (baseDirectory in that).value / "src/test/scala" / dir,
    unmanagedResourceDirectories in Compile += (baseDirectory in that).value / "src/main/resources" / dir,
    unmanagedResourceDirectories in Test    += (baseDirectory in that).value / "src/test/resources" / dir

  )

  def addDirectories(base: File, dir: String = ".") = Seq(
    // pseudo link shared files from jvm source/resource directories to js
    unmanagedSourceDirectories   in Compile += base / "src/main/scala" / dir,
    unmanagedSourceDirectories   in Test    += base / "src/test/scala" / dir,
    unmanagedResourceDirectories in Compile += base / "src/main/resources" / dir,
    unmanagedResourceDirectories in Test    += base / "src/test/resources" / dir

  )

  def linkedSources(sharedSrc: Project) = Seq(
    // pseudo link shared files from jvm source/resource directories to js
    unmanagedSourceDirectories in Compile ++= (unmanagedSourceDirectories in(sharedSrc, Compile)).value,
    unmanagedSourceDirectories in Test ++= (unmanagedSourceDirectories in(sharedSrc, Test)).value,
    unmanagedResourceDirectories in Compile ++= (unmanagedResourceDirectories in(sharedSrc, Compile)).value,
    unmanagedResourceDirectories in Test ++= (unmanagedResourceDirectories in(sharedSrc, Test)).value
  )

  def sjsResources(prjJs: Project) = Seq(
    unmanagedResourceDirectories in Compile += (crossTarget in fastOptJS in Compile in prjJs).value,
    unmanagedResourceDirectories in Test += (crossTarget in fastOptJS in Test in prjJs).value,
    unmanagedResources in Test += ((packageJSDependencies in Compile) in prjJs).value,
    unmanagedResources in Test += ((packageJSDependencies in Test) in prjJs).value,
    unmanagedResources in Compile += ((artifactPath in fastOptJS in Compile) in prjJs).value,
    unmanagedResources in Test += ((artifactPath in fastOptJS in Compile) in prjJs).value,
    unmanagedResources in Test += ((artifactPath in fastOptJS in Test) in prjJs).value,

    copyResources in Test <<= (copyResources in Test) dependsOn (fastOptJS in Test in prjJs)
  )

  val scalajsJvmSettings = Seq(target := target.value / "jvm")
  val scalajsJsSettings  = Seq(target := target.value / "js")
}
