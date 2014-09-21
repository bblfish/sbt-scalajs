// Comment to get more information during initialization
logLevel := Level.Warn

// The Typesafe repository 
resolvers += "Typesafe repository" at "http://repo.typesafe.com/typesafe/releases/"

resolvers += Resolver.mavenLocal

 
 addSbtPlugin("com.typesafe.sbt" % "sbt-web" % "1.1.0")

 addSbtPlugin("com.typesafe.sbt" % "sbt-coffeescript" % "1.0.0")

 addSbtPlugin("com.inthenow.sbt" % "sbt-scalajs" % "0.1.0-SNAPSHOT")
