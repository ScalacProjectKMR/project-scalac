package server.model

sealed abstract case class CustomerId private (value: String)

object CustomerId {

  def fromString(value: String): Option[CustomerId] =
    Option.when(value.length() > 1)(new CustomerId(value) {})
}
