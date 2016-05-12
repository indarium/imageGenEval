package actor

import akka.actor.Actor
import spray.http.MediaTypes
import spray.routing.HttpService
import MediaTypes._

/**
  * author: cvandrei
  * since: 2016-05-12
  */
class EtagServiceActor extends Actor with EtagService {

  def actorRefFactory = context

  def receive = runRoute(etagRoutes)

}

trait EtagService extends HttpService {

  val etagRoutes =
    path("") {
      get {
        respondWithMediaType(`application/json`) {
          complete {
            """{status: "ok"}"""
          }
        }
      }
    }

}