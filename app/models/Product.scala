package models

import play.api.libs.json._

case class Product(id: Long, name: String, description: String, keyword: String, category: Int)

object Product {
  implicit val productJSON = Json.format[Product]
}