package models.product

import play.api.libs.json._

case class Product(id: Long, name: String, description: String, categoryId: Int)

object Product {
  implicit val productJSON = Json.format[Product]
}