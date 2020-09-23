package com.example.integration

import org.apache.logging.log4j.scala.Logging
import org.scalatest.funspec.AnyFunSpec
import org.scalatest.matchers._

abstract class IntegrationSpec extends AnyFunSpec with should.Matchers with
  TestDataProvider with Logging {

  val apiAddress = "https://itunes.apple.com/search"
  val termParam = "term"
  val limitParam = "limit"
}