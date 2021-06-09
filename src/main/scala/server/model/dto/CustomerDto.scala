package server.model.dto

import server.model._

case class CustomerDto(email: CustomerEmail, name: CustomerName, phone: CustomerPhone)