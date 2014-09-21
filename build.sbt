sbtPlugin := true

organization := "com.github.inthenow"

name := "sbt-scalajs"

version := "0.1.0"

scalaVersion := "2.10.4"

scalacOptions ++= Seq("-deprecation", "-unchecked")

resolvers ++= Seq(
  Resolver.sbtPluginRepo("releases"),
  Resolver.sonatypeRepo("releases"),
  Resolver.typesafeRepo("releases"),
  Resolver.url("scala-js-releases",
    url("http://bintray.com/scala-js/scala-js-releases"))(Resolver.ivyStylePatterns)
)

libraryDependencies ++= Seq(
  "junit" % "junit" % "4.11" % "test"
)

addSbtPlugin("org.scala-lang.modules.scalajs" % "scalajs-sbt-plugin" % "0.5.5")

addSbtPlugin("com.typesafe.sbt" % "sbt-web" % "1.1.0")

addSbtPlugin("com.typesafe.sbt" % "sbt-js-engine" % "1.0.1")

publishMavenStyle := true

publishArtifact in Test := false

publishTo := {
  val nexus = "http://johnsonusm.com:8020/nexus/"
  if (isSnapshot.value)
    Some("Sonatype Snapshots" at nexus + "content/repositories/snapshots/")
  else
    Some("Sonatype Snapshots" at nexus + "content/repositories/releases/")
}

pomIncludeRepository := { _ => false}

pomExtra := <url>https://github.com/InTheNow</url>
  <developers>
    <developer>
      <id>Alistair</id>
      <name>Johnson</name>
      <url>https://github.com/InTheNow</url>
    </developer>
  </developers>
  <scm>
    <url>git@github.com:InTheNow/sbt-scalajs.git</url>
    <connection>git@github.com:InTheNow/sbt-scalajs.git</connection>
  </scm>

licenses +=("Apache License, Version 2.0", url("http://www.apache.org/licenses/LICENSE-2.0"))
