name := "api-tests-scala"
version := "0.1"
scalaVersion := "2.13.3"

libraryDependencies += "org.scala-lang.modules" %% "scala-parser-combinators" % "1.1.2"
libraryDependencies += "org.scalactic" %% "scalactic" % "3.2.0"
libraryDependencies += "org.scalatest" %% "scalatest" % "3.2.0" % Test

// for unit tests
libraryDependencies += "com.typesafe.akka" %% "akka-testkit" % "2.6.9" % Test
libraryDependencies += "com.typesafe.akka" %% "akka-http-testkit" % "10.2.0" % Test
libraryDependencies += "com.typesafe.akka" %% "akka-stream" % "2.6.9"

// for integration tests
libraryDependencies += "org.scalaj" %% "scalaj-http" % "2.4.2"
libraryDependencies += "com.typesafe.play" %% "play-json" % "2.9.1"

// logging
libraryDependencies ++= Seq(
  "org.apache.logging.log4j" %% "log4j-api-scala" % "12.0",
  "org.apache.logging.log4j" % "log4j-core" % "2.13.0" % Runtime
)

testOptions in Test += Tests.Argument(TestFrameworks.ScalaTest, "-y", "org.scalatest.flatspec.AnyFlatSpec",
  "-y", "org.scalatest.fixture.AnyFlatSpec")
