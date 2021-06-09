package server.model

sealed abstract case class CustomerPhone private (value: String)

object CustomerPhone {

  private val PhonePattern = "/^[0-9\\+]{1,}[0-9\\-]{3,15}$/".r

  def fromString(value: String): Option[CustomerPhone] =
    PhonePattern.unapplySeq(value).map(_ => new CustomerPhone(value) {})
}
