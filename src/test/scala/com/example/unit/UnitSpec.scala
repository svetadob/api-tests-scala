package com.example.unit

import akka.http.scaladsl.testkit.ScalatestRouteTest
import org.scalatest.funspec.AnyFunSpec
import org.scalatest.matchers._

abstract class UnitSpec extends AnyFunSpec with should.Matchers with ScalatestRouteTest