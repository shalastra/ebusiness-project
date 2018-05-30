package models.orderdetails

import play.api.libs.json.Json

case class OrderDetails(id: Int, orderDate: String, productPrice: Double,
                        quantity: Int, totalPrice: Double, orderId: Int, productId: Int)

object OrderDetails {
  implicit val orderDetailsJSON = Json.format[OrderDetails]
}
