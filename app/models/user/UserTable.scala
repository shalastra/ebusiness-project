package models.user

import models.SlickRepository

trait UserTable {

  this: SlickRepository =>

  import driver.api._

  protected class UserTable(tag: Tag) extends Table[User](tag, "user") {
    def id = column[Long]("id", O.PrimaryKey, O.AutoInc)
    def username = column[Option[String]]("username")
    def email = column[Option[String]]("email")
    def token = column[String]("token")

    def * = (id, username, email, token) <> ((User.apply _).tupled, User.unapply)
  }

  protected val user = TableQuery[UserTable]
}
