package models.orderdetails

import models.SlickRepository

trait OrderDetailsTable {
  this: SlickRepository =>

  import driver.api._

  protected class OrderDetailsTable(tag: Tag) extends Table[OrderDetails](tag, "orderdetails") {
    def id = column[Long]("id", O.PrimaryKey, O.AutoInc)
    def orderDate = column[String]("orderDate")
    def productPrice = column[Double]("orderPrice")
    def quantity = column[Int]("quantity")
    def totalPrice = column[Double]("totalPrice")
    def orderId = column[Long]("orderId")
    def productId = column[Long]("productId")

    def * = (id, orderDate, productPrice, quantity, totalPrice,
      orderId, productId) <> ((OrderDetails.apply _).tupled, OrderDetails.unapply)
  }

  protected val orderDetails = TableQuery[OrderDetailsTable]
}
