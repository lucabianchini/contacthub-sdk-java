organization := "com.contactlab"
name := "ContactHUB Java SDK"
version := "1.0-SNAPSHOT"
autoScalaLibrary := false
scalaVersion := scalaV
libraryDependencies ++= testDepedencies ++ dependencies

lazy val scalaV = "2.11.8"

lazy val testDepedencies = Seq(
  "org.scalatest" %% "scalatest" % "3.0.0",
  "org.scala-lang" % "scala-library" % scalaV,
  "org.scala-lang.modules" % "scala-java8-compat_2.11" % "0.8.0-RC7"
).map(_ % "test")

lazy val dependencies = Seq(
  "com.mashape.unirest" % "unirest-java" % "1.4.9",
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
