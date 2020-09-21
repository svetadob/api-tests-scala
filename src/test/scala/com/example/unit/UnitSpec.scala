package com.example.unit

import akka.http.scaladsl.testkit.ScalatestRouteTest
import org.scalatest.matchers._
import org.scalatest.wordspec.AnyWordSpec

abstract class UnitSpec extends AnyWordSpec with should.Matchers with ScalatestRouteTest