package models.product

import models.SlickRepository
import models.category.CategoryTable

trait ProductTable extends CategoryTable{
  this: SlickRepository =>

  import driver.api._

  protected class ProductTable(tag: Tag) extends Table[Product](tag, "product") {

    def id = column[Long]("id", O.PrimaryKey, O.AutoInc)
    def name = column[String]("name")
    def description = column[String]("description")
    def categoryId = column[Int]("category")
    def category_fk = foreignKey("cat_fk", categoryId, category)(_.id)

    def * = (id, name, description, categoryId) <> ((Product.apply _).tupled, Product.unapply)
  }

  protected val product = TableQuery[ProductTable]
}
