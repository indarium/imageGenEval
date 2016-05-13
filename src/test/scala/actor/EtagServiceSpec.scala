package actor

import akka.actor.ActorRefFactory
import org.specs2.mutable.Specification
import spray.http.StatusCodes._
import spray.http.HttpHeaders._
import spray.testkit.Specs2RouteTest

/**
  * author: cvandrei
  * since: 2016-05-12
  */
class EtagServiceSpec extends Specification with Specs2RouteTest with EtagService {

  def actorRefFactory: ActorRefFactory = system

  import json.Json4sSupport._

  "EtagService" should {

    """return status: "ok" for GET requests to the root path""" in {
      Get().withHeaders(ETag("406161ad525c9bdf02a21db721f2ffeb")) ~> routes ~> check {
        status === OK
        val response = responseAs[FooResponse]
        response.status must_== "ok"
        response.method must_== "GET"
        response.headerCount must_== 1
      }
    }

    "leave GET requests to other paths unhandled" in {
      Get("/unhandled") ~> routes ~> check {
        handled must beFalse
      }
    }

    "return a MethodNotAllowed error for PUT requests to the root path" in {
      Put() ~> sealRoute(routes) ~> check {
        status === MethodNotAllowed
//        responseAs[String] === "HTTP method not allowed, supported methods: GET" // TODO fix
      }
    }
  }

}
