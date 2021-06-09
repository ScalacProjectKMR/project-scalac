package server.model

case class Customer(
  id: CustomerId,
  name: CustomerName,
  email: CustomerEmail,
  phone: CustomerPhone
)
