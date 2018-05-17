package models

import play.api.libs.json.Json

case class CartProduct(id: Long, cartId: Long, productId: Long, amount: Int)

object CartProduct {
  implicit val cartProductJSON = Json.format[CartProduct]
}