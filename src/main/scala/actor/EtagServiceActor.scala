package actor

import akka.actor.Actor
import spray.http.HttpHeader
import spray.http.MediaTypes._
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

  /********* PARTIAL MATCHERS *********/

  private val headers = extract(_.request.headers)

  /********* PATH MATCHERS *********/

  private val indexGetHeaders = path("") & get & headers

  /********* PARTIAL ROUTES *********/

  private def indexResponse(headers: List[HttpHeader]): Route = {

    respondWithMediaType(`application/json`) {
      complete {
        s"""{
            |  {status: "ok"},
            |  {method: "GET"},
            |  {headerCount: ${headers.size}}
            |}""".stripMargin
      }
    }

  }

  /********* ROUTING *********/

  val routes: Route =
    indexGetHeaders { headers =>
      indexResponse(headers)
    }

}