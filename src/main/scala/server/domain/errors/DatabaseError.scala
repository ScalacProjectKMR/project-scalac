package server.domain.errors

final case class DatabaseError(message: String) extends ServerError

object DatabaseError extends (Throwable => DatabaseError) {
  override def apply(error: Throwable): DatabaseError = new DatabaseError(error.getMessage)
}
