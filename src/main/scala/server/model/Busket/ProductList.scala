package server.model

sealed abstract case class ProductList private (value: List[Product])

object ProductList {

  def fromString(value: List[Product]): Option[ProductList] =
    Option(new ProductList(value) {})
}
