package models

import play.api.libs.json.Json

case class Order(id: Long, cartId: Long, orderDate: Double)

object Order {
  implicit val orderJSON = Json.format[Order]
}
