package server.model

sealed abstract case class ProductName private (value: String)

object ProductName {

  def fromString(value: String): Option[ProductName] =
    Option.when(value.length() > 1)(new ProductName(value) {})
}
