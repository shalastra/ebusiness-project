package models

import play.api.libs.json.Json

case class Payment(id: Int, paymentType: String)

object Payment {
  implicit val paymentJSON = Json.format[Payment]
}