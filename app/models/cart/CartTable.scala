package models.cart

import models.SlickRepository

trait CartTable {
  this: SlickRepository => // has to be mixed in with SlickRepository

  import driver.api._

  protected class CartTable(tag: Tag) extends Table[Cart](tag, "cart") {
    def id = column[Long]("id", O.PrimaryKey, O.AutoInc)
    def orderId = column[Long]("orderId")

    def * = (id, orderId) <> ((Cart.apply _).tupled, Cart.unapply)
  }

  protected val cart = TableQuery[CartTable]
}
