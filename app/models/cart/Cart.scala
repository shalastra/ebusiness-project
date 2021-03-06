package models.cart

import play.api.libs.json.Json

case class Cart(id: Long, orderId: Long)

object Cart {
  implicit val cartJSON = Json.format[Cart]
}