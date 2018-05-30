package models.review

import models.SlickRepository
import models.product.ProductTable

trait ReviewTable extends ProductTable {

  this: SlickRepository =>

  import driver.api._

  protected class ReviewTable(tag: Tag) extends Table[Review](tag, "review") {
    def id = column[Long]("id", O.PrimaryKey, O.AutoInc)
    def grade = column[Int]("grade")
    def comment = column[String]("paymentType")
    def productId = column[Long]("productId")
    def product_fk = foreignKey("product_fk", productId, product)(_.id)

    def * = (id, grade, comment, productId) <> ((Review.apply _).tupled, Review.unapply)
  }

  protected val review = TableQuery[ReviewTable]
}
