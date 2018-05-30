package models.review

import play.api.libs.json.Json

case class Review(id: Long, grade: Int, comment: String, productId: Long)

object Review {
  implicit val reviewJSON = Json.format[Review]
}
