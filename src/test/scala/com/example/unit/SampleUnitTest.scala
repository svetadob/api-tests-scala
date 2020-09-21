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

  "The service" should {

    "return a greeting for GET requests to the root path" in {
      Get() ~> Route.seal(testRoute) ~> check {
        responseAs[String] shouldEqual "The requested resource could not be found."
      }
    }

    "return a 'Test is successful!' response for GET requests to /test" in {
      Get("/test") ~> testRoute ~> check {
        responseAs[String] shouldEqual "Test is successful!"
      }
    }

    "return PUT method not allowed" in {
      Put() ~> Route.seal(testRoute) ~> check {
        status shouldEqual StatusCodes.MethodNotAllowed
        responseAs[String] shouldEqual "HTTP method not allowed, supported methods: GET"
      }
    }
  }
}
