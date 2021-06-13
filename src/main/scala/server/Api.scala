package server

import akka.http.scaladsl.server.Route
import akka.http.interop.ZIOSupport
import akka.http.scaladsl.server.Directives.{as => as_, _}
import de.heikoseeberger.akkahttpcirce.FailFastCirceSupport._
import io.circe.generic.auto._
import server.application.{CustomerService, EchoService, ProductService, SystemService}
import server.model.{CustomerId, ProductId}
import server.model.dto._
import zio.{Has, ZIO, ZLayer}
import shapeless.ops.product

trait Api {
  def routes: Route
}

object Api {

  val live: ZLayer[Has[EchoService] with Has[ProductService] with Has[CustomerService] with Has[
    SystemService
  ], Nothing, Has[Api]] =
    ZLayer.fromServices[EchoService, ProductService, CustomerService, SystemService, Api] {
      (echoService, productService, customerService, systemService) =>
        new Api with JsonSupport with ZIOSupport {
          val routes: Route =
            pathPrefix("health") {
              get {
                complete(ZIO.succeed("OK"))
              }
            } ~ pathPrefix("system") {
              (path("info") & get) {
                complete(systemService.info())
              }
            } ~ pathPrefix("echo") {
              post {
                entity(as_[String]) { data =>
                  complete(echoService.ping(data))
                }
              }
            } ~ pathPrefix("product") {
              post {
                entity(as_[ProductDto]) { request =>
                  complete(productService.create(request.name, request.price, request.quantity))
                }
              } ~
                delete {
                  path(Segment) { id =>
                    complete(productService.delete(id))
                  }
                } ~
                put {
                  path(Segment) { id =>
                    entity(as_[ProductDto]) { request =>
                      complete(productService.put(id, request.name, request.price, request.quantity))
                    }
                  }
                }
            } ~ pathPrefix("customer") {
              pathPrefix("basket" / Segment) { id =>
                entity(as_[ProductBusketDto]) { request =>
                  post {
                    complete(customerService.postBasket(id, request.id, request.quantity))
                  } ~
                    delete {
                      complete(customerService.deleteBasket(id, request.id, request.quantity))
                    } ~
                    put {
                      complete(customerService.putBasket(id, request.id, request.quantity))
                    }
                }
              } ~
                pathPrefix(Segment / "buy") { id =>
                  complete(customerService.buy(id))
                } ~
                post {
                  entity(as_[CustomerDto]) { request =>
                    complete(customerService.create(request.name, request.email, request.phone))
                  }
                } ~
                delete {
                  path(Segment) { id =>
                    complete(customerService.delete(id))
                  }
                } ~
                put {
                  path(Segment) { id =>
                    entity(as_[CustomerDto]) { request =>
                      complete(customerService.put(id, request.name, request.email, request.phone))
                    }
                  }
                }
            }
        }
    }
}
