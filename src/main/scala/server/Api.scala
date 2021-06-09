package server

import akka.http.scaladsl.server.Route
import akka.http.interop.ZIOSupport
import akka.http.scaladsl.server.Directives.{ as => as_, _ }
import de.heikoseeberger.akkahttpcirce.FailFastCirceSupport._
import io.circe.generic.auto._
import server.application.{ EchoService, SystemService, UserService }
import server.model.UserId
import server.model.dto.CreateUserRequest
import zio.{ Has, ZIO, ZLayer }

trait Api {
  def routes: Route
}

object Api {

  // custom path matcher
  val UserIdVal = IntNumber.flatMap(UserId.fromInt)

  val live: ZLayer[Has[EchoService] with Has[UserService] with Has[SystemService], Nothing, Has[Api]] =
    ZLayer.fromServices[EchoService, UserService, SystemService, Api] { (echoService, userService, systemService) =>
      new Api with JsonSupport with ZIOSupport {
        val routes: Route =
          pathPrefix("health") {
            get {
              complete(ZIO.succeed("OK"))
            }
          } ~ pathPrefix("echo") {
            post {
              // Note: this is normal `as` directive, however had to be renamed due to name clash
              entity(as_[String]) { data =>
                complete(echoService.ping(data))
              }
            }
          } ~ pathPrefix("users") {
            post {
              entity(as_[CreateUserRequest]) { request =>
                complete(userService.create(request.name, request.age))
              }
            } ~
            get {
              path(UserIdVal) { id =>
                complete(userService.find(id))
              } ~ pathEndOrSingleSlash {
                complete(userService.get())
              }
            }
          } ~ pathPrefix("system") {
            (path("info") & get) {
              complete(systemService.info())
            }
          }
      }
    }
}
