package server.model

sealed abstract case class UserId private (value: Int)

object UserId {

  def fromInt(value: Int): Option[UserId] =
    Option.when(value >= 0)(new UserId(value) {})
}
