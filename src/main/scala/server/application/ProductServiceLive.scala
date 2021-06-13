package server.application

import server.domain.services._
import server.domain.repositories._
import server.domain.errors._
import server.domain.errors.ServiceError._
import zio._
import server.model.Product._

final class ProductServiceLive(repository: ProductRepository, readService: ProductReadService) extends ProductService {
  override def findById(id: String): IO[ServerError, Product] = readService.getById(id).someOrFail(ProductNotFound)
  override def insert(entity: Product)
}
