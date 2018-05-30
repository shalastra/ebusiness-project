package models.order

import models.SlickRepository

trait OrderTable {
  this: SlickRepository =>

  import driver.api._

  protected class OrderTable(tag: Tag) extends Table[Order](tag, "order") {
    def id = column[Long]("id", O.PrimaryKey, O.AutoInc)
    def orderDate = column[String]("orderDate")
    def userId = column[Long]("userId")
    def paymentId = column[Long]("paymentId")

    def * = (id, orderDate, userId, paymentId) <> ((Order.apply _).tupled, Order.unapply)
  }

  protected val order = TableQuery[OrderTable]
}
