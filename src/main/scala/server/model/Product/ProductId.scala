package server.model

sealed abstract case class ProductId private (value: String)

object ProductId {

  def fromString(value: String): Option[ProductId] =
    Option.when(value.length() > 1)(new ProductId(value) {})
}
