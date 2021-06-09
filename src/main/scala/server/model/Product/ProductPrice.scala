package server.model

sealed abstract case class ProductPrice private (value: BigDecimal)

object ProductPrice {

  def fromBigDecimal(value: BigDecimal): Option[ProductPrice] =
    Option.when(value > 0)(new ProductPrice(value) {})
}
