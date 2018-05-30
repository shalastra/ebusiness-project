package models.orderdetails

import play.api.libs.json.Json

case class OrderDetails(id: Long, orderDate: String, productPrice: Double,
                        quantity: Int, totalPrice: Double, orderId: Long, productId: Long)

object OrderDetails {
  implicit val orderDetailsJSON = Json.format[OrderDetails]
}
