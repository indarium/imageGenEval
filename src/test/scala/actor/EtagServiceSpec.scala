package actor

import akka.actor.ActorRefFactory
import org.specs2.mutable.Specification
import spray.http.StatusCodes._
import spray.testkit.Specs2RouteTest

/**
  * author: cvandrei
  * since: 2016-05-12
  */
class EtagServiceSpec extends Specification with Specs2RouteTest with EtagService {

  def actorRefFactory: ActorRefFactory = system

  "EtagService" should {

    "return a greeting for GET requests to the root path" in {
      Get() ~> etagRoutes ~> check {
        responseAs[String] must_== """{status: "ok"}"""
      }
    }

    "leave GET requests to other paths unhandled" in {
      Get("/unhandled") ~> etagRoutes ~> check {
        handled must beFalse
      }
    }

    "return a MethodNotAllowed error for PUT requests to the root path" in {
      Put() ~> sealRoute(etagRoutes) ~> check {
        status === MethodNotAllowed
        responseAs[String] === "HTTP method not allowed, supported methods: GET"
      }
    }
  }

}
