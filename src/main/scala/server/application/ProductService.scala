package server.application

import server.model._
import zio._
import zio.logging.Logger

trait ProductService {

  def find(id: String): UIO[Option[Product]]

  def get(): UIO[List[Product]]

  def create(name: ProductName, price: ProductPrice, quantity: ProductQuantity): UIO[Unit]

  def delete(id: String): UIO[Unit]

  def put(id: String, name: ProductName, price: ProductPrice, quantity: ProductQuantity): UIO[Unit]

}

object ProductService {
  ???
}
