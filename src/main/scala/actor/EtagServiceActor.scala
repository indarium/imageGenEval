package actor

import akka.actor.Actor
import spray.http.HttpHeader
import spray.routing.{HttpService, Route}

/**
  * author: cvandrei
  * since: 2016-05-12
  */
class EtagServiceActor extends Actor with EtagService {

  def actorRefFactory = context

  def receive = runRoute(routes)

}

trait EtagService extends HttpService {

  import json.Json4sSupport._

  /** ******* PARTIAL MATCHERS *********/

  private val headers = extract(_.request.headers)

  /** ******* PATH MATCHERS *********/

  private val indexGetHeaders = path("") & get & headers

  private val fbcWithoutCanvas1stPartyCookie = path("/v1/fbc") & get & parameters('eid.as[Long], 'acc.as[Long]) & headers

  // TODO match correct parameters
  private val fbcWithoutCanvas3rdPartyCookie = path("/v1/fbc") & get & parameters('v.as[Long], 'acc.as[Long]) & headers

  private val fbcWithCanvas = path("/v1/fbc") & get & headers

  /** ******* PARTIAL ROUTES *********/

  private def indexResponse(headers: List[HttpHeader]): Route = {

    complete(FooResponse(headerCount = headers.size))

  }

  private def fbcWithoutCanvas1stPartyCookieResponse(eid: Long, acc: Long, headers: List[HttpHeader]): Route = {

    complete(FooResponse(status = "etag", headerCount = 0))

  }

  private def fbcWithoutCanvas3rdPartyCookieResponse(v: Long, acc: Long, headers: List[HttpHeader]): Route = {

    complete(FooResponse(status = "etag", headerCount = 0))

  }

  private def fbcWithCanvasResponse(headers: List[HttpHeader]): Route = {
    complete(FooResponse(status = "withCanvas", headerCount = 0))
  }

  /** ******* ROUTING *********/

  val routes: Route =

    indexGetHeaders { headers =>
      indexResponse(headers)
    } ~
      fbcWithoutCanvas1stPartyCookie { (eid, acc, headers) =>
        fbcWithoutCanvas1stPartyCookieResponse(eid, acc, headers)
      } ~
      fbcWithoutCanvas3rdPartyCookie { (v, acc, headers) => // TODO use correct parameter names
        fbcWithoutCanvas3rdPartyCookieResponse(v, acc, headers)
      } ~
      fbcWithCanvas { headers =>
        fbcWithCanvasResponse(headers)
      }

}

case class FooResponse(status: String = "ok", method: String = "GET", headerCount: Int)
