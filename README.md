sbt-scalajs
=======

An SBT plugin for [Scala,js](http://www.scala-js.org/).

This plugin wraps the plugin supplied with scalajs as an auto-plugin and adds some pre-defined settings for cross-compiling 
projects. Otherwise, usage ans settings is a per the original.

Usage
=====

To use this plugin, add a Resolver to the plugin and use the addSbtPlugin command within your project's `plugins.sbt` file:

    resolvers += "JohnsonUSM snapshots" at "http://johnsonusm.com:8020/nexus/content/repositories/releases/"
    
    addSbtPlugin("com.github.inthenow" % "sbt-scalajs" % "0.1.0")

Your project's build file also needs to enable sbt-scalajs plugins. For example with build.sbt:

    lazy val root = (project in file(".")).enablePlugins(SbtScalajs)

Examples
========

* For a cross-compiling project, see [scalatest-jasmine](https://github.com/InTheNow/scalatest-jasmine)
* For a multi-module, cross-compiling and cross-testing project, see [the jasminr branch of banana-rdf](https://github.com/InTheNow/banana-rdf/tree/jasmine)

Experimental
============

Another (not yet fully functional) plugin **SbtScalajsWeb**  is also included that adds support for [SbtWeb](https://github.com/sbt/sbt-web)

Licence
=======

Copyright &copy; 2014 Alistair Johnson

Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with the License. You may obtain a copy of the License at

http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the specific language governing permissions and limitations under the License.