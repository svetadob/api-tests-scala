package com.example.unit

import akka.http.scaladsl.model.StatusCodes
import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server._

import scala.language.postfixOps

class SampleUnitTest extends UnitSpec {

  val testRoute: Route =
    get {
      pathSingleSlash {
        complete {
          "Default path"
        }
      }
      path("test") {
        complete("Test is successful!")
      }
    }

  describe("The service") {

    it("return a greeting for GET requests to the root path") {
      Get() ~> Route.seal(testRoute) ~> check {
        responseAs[String] shouldEqual "The requested resource could not be found."
      }
    }

    it("return a 'Test is successful!' response for GET requests to /test") {
      Get("/test") ~> testRoute ~> check {
        responseAs[String] shouldEqual "Test is successful!"
      }
    }

    it("return PUT method not allowed") {
      Put() ~> Route.seal(testRoute) ~> check {
        status shouldEqual StatusCodes.MethodNotAllowed
        responseAs[String] shouldEqual "HTTP method not allowed, supported methods: GET"
      }
    }
  }
}
