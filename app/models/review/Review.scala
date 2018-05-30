package models.review

import play.api.libs.json.Json

case class Review(id: Int, grade: Int, comment: String, productId: Int)

object Review {
  implicit val reviewJSON = Json.format[Review]
}
