package server

import io.circe.{Decoder, Encoder}
import server.model._

trait JsonSupport {

  implicit val customerEmailEncoder: Encoder[CustomerEmail] = Encoder.encodeString.contramap[CustomerEmail](_.value)

  implicit val userIdDecoder: Decoder[CustomerEmail] =
    Decoder.decodeString.emap(CustomerEmail.fromString(_).toRight("Invalid customer email."))

  implicit val customerIdEncoder: Encoder[CustomerId] = Encoder.encodeString.contramap[CustomerId](_.value)

  implicit val customerIdDecoder: Decoder[CustomerId] =
    Decoder.decodeString.emap(CustomerId.fromString(_).toRight("Invalid customer id."))

  implicit val customerNameEncoder: Encoder[CustomerName] = Encoder.encodeString.contramap[CustomerName](_.value)

  implicit val customerNameDecoder: Decoder[CustomerName] =
    Decoder.decodeString.emap(CustomerName.fromString(_).toRight("Invalid customer name."))

  implicit val customerPhoneEncoder: Encoder[CustomerPhone] = Encoder.encodeString.contramap[CustomerPhone](_.value)

  implicit val customerPhoneDecoder: Decoder[CustomerPhone] =
    Decoder.decodeString.emap(CustomerPhone.fromString(_).toRight("Invalid customer phone."))

  implicit val productIdEncoder: Encoder[ProductId] = Encoder.encodeString.contramap[ProductId](_.value)

  implicit val productIdDecoder: Decoder[ProductId] =
    Decoder.decodeString.emap(ProductId.fromString(_).toRight("Invalid product id."))

  implicit val productNameEncoder: Encoder[ProductName] = Encoder.encodeString.contramap[ProductName](_.value)

  implicit val productNameDecoder: Decoder[ProductName] =
    Decoder.decodeString.emap(ProductName.fromString(_).toRight("Invalid product name."))

  implicit val productPriceEncoder: Encoder[ProductPrice] = Encoder.encodeBigDecimal.contramap[ProductPrice](_.value)

  implicit val productPriceDecoder: Decoder[ProductPrice] =
    Decoder.decodeBigDecimal.emap(ProductPrice.fromBigDecimal(_).toRight("Invalid product price."))

  implicit val productQuantityEncoder: Encoder[ProductQuantity] = Encoder.encodeInt.contramap[ProductQuantity](_.value)

  implicit val productQuantityDecoder: Decoder[ProductQuantity] =
    Decoder.decodeInt.emap(ProductQuantity.fromInt(_).toRight("Invalid product quantity."))
}
