package server.model

sealed abstract case class Age private (value: Int)

object Age {

  def fromInt(value: Int): Option[Age] =
    Option.when(value >= 0 && value < 150)(new Age(value) {})
}
