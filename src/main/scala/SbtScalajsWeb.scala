package com.inthenow.sbt.scalajs

import sbt._
import sbt.Keys._
import com.typesafe.sbt.web._
import scala.scalajs.tools.io.{IO => _, _}

object SbtScalajsWeb extends AutoPlugin {

  override def requires = SbtWeb

  override def trigger = noTrigger

  val autoImport = Import

  import SbtWeb.autoImport._
  import WebKeys._
  import scala.scalajs.sbtplugin.ScalaJSPlugin.ScalaJSKeys._

  override def globalSettings: Seq[Setting[_]] = SbtWeb.globalSettings

  def sbtWebDefaults = Seq(
    webTarget := crossTarget.value / "web",
    classDirectory in Assets := createClassDirectory((webModuleDirectory in Assets).value),
    classDirectory in TestAssets := createClassDirectory((webModuleDirectory in TestAssets).value)
  )

  def linkedAssets(sharedSrc: Project) = Seq(

    unmanagedSourceDirectories in Assets ++= (unmanagedSourceDirectories in(sharedSrc, Assets)).value,
    unmanagedSourceDirectories in TestAssets ++= (unmanagedSourceDirectories in(sharedSrc, TestAssets)).value,
    unmanagedResourceDirectories in Assets ++= (unmanagedResourceDirectories in(sharedSrc, Assets)).value,
    unmanagedResourceDirectories in TestAssets ++= (unmanagedResourceDirectories in(sharedSrc, TestAssets)).value

  )

  val preScalaJSSettings = SbtScalajs.preScalaJSSettings

  val postScalaJSSettings = SbtScalajs.postScalaJSSettings

  val preSbtWebSettings = {
    Seq(
    )
  }

  val postSbtWebSettings = {
    Seq(
      webTarget := crossTarget.value / "web",
      classDirectory in Assets := createClassDirectory((webModuleDirectory in Assets).value),
      classDirectory in TestAssets := createClassDirectory((webModuleDirectory in TestAssets).value),

      unmanagedSources in Assets ++= Seq((artifactPath in fastOptJS in Compile).value),
      unmanagedSourceDirectories in Assets ++= Seq((crossTarget in fastOptJS in Compile).value / "scalajs_managed"),

      unmanagedSources in TestAssets ++= Seq((artifactPath in fastOptJS in Test).value),
      unmanagedSourceDirectories in TestAssets ++= Seq((crossTarget in fastOptJS in Test).value / "scalajs_managed")
    )
  }

  // This should not really be required
  def createClassDirectory(dir: File) = {

    IO.createDirectory(dir)
    dir
  }

  val baseScalajsSettings = {
    Seq(
    )
  }

  override def projectSettings =
    preSbtWebSettings ++ SbtWeb.projectSettings ++ preScalaJSSettings ++ postSbtWebSettings ++ postScalaJSSettings ++
      Seq(
      )
}
