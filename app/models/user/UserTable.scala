package models.user

import models.SlickRepository

trait UserTable {

  this: SlickRepository =>

  import driver.api._

  protected class UserTable(tag: Tag) extends Table[User](tag, "user") {
    def id = column[Long]("id", O.PrimaryKey, O.AutoInc)
    def username = column[String]("username")
    def password = column[String]("password")

    def * = (id, username, password) <> ((User.apply _).tupled, User.unapply)
  }

  protected val user = TableQuery[UserTable]
}
