organization := "it.contactlab.hub"
name := "sdk-java"
description := "ContactHUB Java SDK"
autoScalaLibrary := false
scalaVersion := scalaV
libraryDependencies ++= testDependencies ++ dependencies
publishMavenStyle := true
crossPaths := false
publishTo := Some("buildo private maven" at "https://buildo-private-maven.appspot.com/")
credentials += Credentials(Path.userHome / ".ivy2" / ".credentials")

enablePlugins(GitVersioning)

lazy val scalaV = "2.11.8"

lazy val testDependencies = Seq(
  "org.scalatest" %% "scalatest" % "3.0.0",
  "org.scala-lang" % "scala-library" % scalaV,
  "org.scala-lang.modules" % "scala-java8-compat_2.11" % "0.8.0-RC7",
  "org.scalacheck" %% "scalacheck" % "1.13.4"
).map(_ % "test")

lazy val dependencies = Seq(
  "com.mashape.unirest" % "unirest-java" % "1.4.9",
  "io.swagger" % "swagger-annotations" % "1.5.10",
  "commons-lang" % "commons-lang" % "2.6",
  "com.google.code.gson" % "gson" % "2.7"
)

lazy val modelsGenerator = project.in(file("models-generator")).
  settings(Seq(
    libraryDependencies ++= Seq(
      "net.jcazevedo" %% "moultingyaml" % "0.3.0"
    )
  ))

lazy val generateModels = taskKey[Unit]("Generate models from swagger.yml")
generateModels := (run in Compile in modelsGenerator).toTask("").value

lazy val root = project.in(file(".")).settings(javaUnidocSettings: _*)
lazy val example = project.in(file("example")).dependsOn(root)
