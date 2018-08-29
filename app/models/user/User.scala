package models.user

import play.api.libs.json.Json

case class User(id: Long, username: Option[String], email: Option[String], token: String)

object User {
  implicit val userJSON = Json.format[User]
}