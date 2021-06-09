package server.model

sealed abstract case class Username private (value: String)

object Username {

  private val Pattern = "^[a-zA-Z]{4,20}$".r

  def fromString(value: String): Option[Username] =
    Pattern.unapplySeq(value).map(_ => new Username(value) {})
}
