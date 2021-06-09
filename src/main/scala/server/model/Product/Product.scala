package server.model

case class Product(
  id: ProductId,
  name: ProductName,
  price: ProductPrice,
  quantity: ProductQuantity,
)