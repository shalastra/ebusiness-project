package models

import play.api.libs.json.Json

case class Order(id: Int, orderDate: String, userId: Int, paymentId: Int)

object Order {
  implicit val orderJSON = Json.format[Order]
}