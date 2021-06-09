package server.model

sealed abstract case class ProductQuantity private (value: Int)

object ProductQuantity {

  def fromInt(value: Int): Option[ProductQuantity] =
    Option.when(value >= 0)(new ProductQuantity(value) {})
}
