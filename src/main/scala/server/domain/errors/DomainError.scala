package server.domain.errors

sealed trait DomainError  extends ServerError
sealed trait ServiceError extends DomainError

object ServiceError {
  case object ProductNotFound extends ServiceError
}
