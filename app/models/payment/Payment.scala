package models.payment

import play.api.libs.json.Json

case class Payment(id: Long, paymentType: String)

object Payment {
  implicit val paymentJSON = Json.format[Payment]
}