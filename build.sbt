name := """play-scala-slick-example"""

version := "2.6.x"

lazy val root = (project in file(".")).enablePlugins(PlayScala)

scalaVersion := "2.12.6"

resolvers += Resolver.jcenterRepo

resolvers += "Sonatype snapshots" at "https://oss.sonatype.org/content/repositories/snapshots/"
crossScalaVersions := Seq("2.11.12", "2.12.4")

libraryDependencies += guice
libraryDependencies += "com.typesafe.play" %% "play-slick" % "3.0.3"
libraryDependencies += "com.typesafe.play" %% "play-slick-evolutions" % "3.0.3"
libraryDependencies += "org.xerial"        %  "sqlite-jdbc" % "3.21.0"

//libraryDependencies += "slick.driver.SQLiteDriver" %%

//libraryDependencies += "com.h2database" % "h2" % "1.4.196"

TwirlKeys.templateImports := Seq()
libraryDependencies += specs2 % Test
libraryDependencies ++= Seq("org.webjars" %% "webjars-play" % "2.6.3")

resolvers += "scalaz-bintray" at "https://dl.bintray.com/scalaz/releases"