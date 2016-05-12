import actor.EtagServiceActor
import akka.actor.{ActorSystem, Props}
import akka.io.IO
import akka.pattern.ask
import akka.util.Timeout
import kamon.Kamon
import spray.can.Http

import scala.concurrent.duration._

/**
  * author: cvandrei
  * since: 2016-05-12
  */
object Boot extends App {

  Kamon.start()

  implicit val system = ActorSystem("on-spray-can")

  val service = system.actorOf(Props[EtagServiceActor], "etag-service")

  implicit val timeout = Timeout(5.seconds)
  IO(Http) ? Http.Bind(service, interface = "localhost", port = 8080)

  Kamon.shutdown()

}
