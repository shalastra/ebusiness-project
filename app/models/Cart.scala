package models

import play.api.libs.json.Json

case class Cart(id: Int, orderId: Int)

object Cart {
  implicit val cartJSON = Json.format[Cart]
}