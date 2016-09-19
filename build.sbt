// edit this for a desired version of chisel
val chiselVersion = System.getProperty("chiselVersion", "latest.release")

lazy val projSettings = Seq(
  organization := "myorganization",

  version := "0.1",

  name := "chiseltemplate",

  scalaVersion := "2.11.7",

  scalacOptions ++= Seq("-deprecation", "-feature", "-unchecked", "-language:reflectiveCalls"),
  resolvers += "typesafe" at "http://repo.typesafe.com/typesafe/releases/",
  // THIS IS VERY IMPORTANT!!! otherwise tests can fail in bizarre ways
  parallelExecution in Test := false,
  // include chisel
  libraryDependencies ++= ( if (chiselVersion != "None" ) ("edu.berkeley.cs" %% "chisel" % chiselVersion) :: Nil; else Nil),

  // the following are scala packages which are needed to run chisel/the tester
  libraryDependencies += "com.novocode" % "junit-interface" % "0.10" % "test",
  libraryDependencies += "org.scalatest" %% "scalatest" % "2.2.4" % "test",
  libraryDependencies += "org.scala-lang" % "scala-compiler" % "2.11.7",

  // You can add any other scala (or chisel) packages here
  libraryDependencies += "com.github.tototoshi" %% "scala-csv" % "1.2.1"
)

// this is how you can include a git repo into your project ( in this case my half baked utils repo )
lazy val chiselUtils = RootProject(uri("git://github.com/da-steve101/chisel-utils.git"))

// create your project, if you have more git dependencies just add the with more .dependsOn(yourgitProj)
lazy val chiselTemplate = (project in file(".")).settings(projSettings: _*).dependsOn(chiselUtils)

