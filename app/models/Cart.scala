package models

import play.api.libs.json.Json

case class Cart(id: Long, date: String)

object Cart {
  implicit val cartJSON = Json.format[Cart]
}
