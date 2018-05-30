package models.category

import models.SlickRepository

trait CategoryTable {
  this: SlickRepository =>

  import driver.api._

  protected class CategoryTable(tag: Tag) extends Table[Category](tag, "category") {
    def id = column[Int]("id", O.PrimaryKey, O.AutoInc)
    def name = column[String]("name")

    def * = (id, name) <> ((Category.apply _).tupled, Category.unapply)
  }

  val category = TableQuery[CategoryTable]
}
