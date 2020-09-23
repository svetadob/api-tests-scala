lazy val scalatest = "org.scalatest" %% "scalatest" % "3.2.0"

name := "api-tests-scala"
version := "0.1"
scalaVersion := "2.13.3"

lazy val root = (project in file("."))
  .configs(IntegrationTest)
  .settings(
    Defaults.itSettings,
    libraryDependencies += scalatest % "it,test"
  )

libraryDependencies ++= Seq(
  "org.scala-lang.modules" %% "scala-parser-combinators" % "1.1.2",
  "org.scalactic" %% "scalactic" % "3.2.0",
)

// for unit tests
libraryDependencies ++= Seq(
  "com.typesafe.akka" %% "akka-testkit" % "2.6.9" % Test,
  "com.typesafe.akka" %% "akka-http-testkit" % "10.2.0" % Test,
  "com.typesafe.akka" %% "akka-stream" % "2.6.9" % Test
)

// for integration tests
libraryDependencies ++= Seq(
  "org.scalaj" %% "scalaj-http" % "2.4.2" % IntegrationTest,
  "com.typesafe.play" %% "play-json" % "2.9.1" % IntegrationTest
)

// logging
libraryDependencies ++= Seq(
  "org.apache.logging.log4j" %% "log4j-api-scala" % "12.0",
  "org.apache.logging.log4j" % "log4j-core" % "2.13.0" % Runtime
)

Test / testOptions += Tests.Argument(TestFrameworks.ScalaTest, "-y", "org.scalatest.funspec.AnyFunSpec")
IntegrationTest / testOptions += Tests.Argument(TestFrameworks.ScalaTest, "-y", "org.scalatest.funspec.AnyFunSpec")
