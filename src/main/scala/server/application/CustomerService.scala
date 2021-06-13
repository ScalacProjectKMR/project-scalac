package server.application

import server.model._
import zio._
import zio.logging.Logger

trait CustomerService {

  def find(id: String): UIO[Option[Customer]]

  def get(): UIO[List[Customer]]

  def create(name: CustomerName, email: CustomerEmail, phone: CustomerPhone): UIO[Unit]

  def delete(id: String): UIO[Unit]

  def put(id: String, name: CustomerName, email: CustomerEmail, phone: CustomerPhone): UIO[Unit]

  def postBasket(basketId: String, productId: ProductId, quantity: ProductQuantity)

  def deleteBasket(basketId: String, productId: ProductId, quantity: ProductQuantity)

  def putBasket(basketId: String, productId: ProductId, quantity: ProductQuantity)

  def buy(id: String)
}

object CustomerService {
  ???
}
