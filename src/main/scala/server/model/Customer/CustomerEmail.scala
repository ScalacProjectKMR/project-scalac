package server.model

sealed abstract case class CustomerEmail private (value: String)

object CustomerEmail {

  private val EmailPattern = "^[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+.[a-zA-Z0-9-.]+$".r

  def fromString(value: String): Option[CustomerEmail] =
    EmailPattern.unapplySeq(value).map(_ => new CustomerEmail(value) {})
}
