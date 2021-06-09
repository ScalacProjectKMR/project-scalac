package server

import io.circe.{ Decoder, Encoder }
import server.model._

trait JsonSupport {

  implicit val userIdEncoder: Encoder[UserId] = Encoder.encodeInt.contramap[UserId](_.value)
  implicit val userIdDecoder: Decoder[UserId] = Decoder.decodeInt.emap(UserId.fromInt(_).toRight("Invalid user id."))

  implicit val usernameEncoder: Encoder[Username] = Encoder.encodeString.contramap[Username](_.value)
  implicit val usernameDecoder: Decoder[Username] = Decoder.decodeString.emap(Username.fromString(_).toRight("Invalid username"))

  implicit val ageEncoder: Encoder[Age] = Encoder.encodeInt.contramap[Age](_.value)
  implicit val ageDecoder: Decoder[Age] = Decoder.decodeInt.emap(Age.fromInt(_).toRight("Invalid age."))
}
