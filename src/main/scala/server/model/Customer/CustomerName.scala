package server.model

sealed abstract case class CustomerName private (value: String)

object CustomerName {

  def fromString(value: String): Option[CustomerName] =
    Option.when(value.length() > 1)(new CustomerName(value) {})
}
