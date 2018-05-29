package models

import play.api.libs.json.Json

case class Order(id: Long, orderDate: String, userId: Long, paymentId: Long)

object Order {
  implicit val orderJSON = Json.format[Order]
}