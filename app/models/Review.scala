package models

import play.api.libs.json.Json

case class Review(id: Long, description: String, productId: Long)

object Review {
  implicit val reviewJSON = Json.format[Review]
}
