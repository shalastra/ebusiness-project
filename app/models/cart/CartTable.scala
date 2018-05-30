package models.cart

import models.SlickRepository
import models.order.OrderTable

trait CartTable extends OrderTable {
  this: SlickRepository =>

  import driver.api._

  protected class CartTable(tag: Tag) extends Table[Cart](tag, "cart") {
    def id = column[Long]("id", O.PrimaryKey, O.AutoInc)
    def orderId = column[Long]("orderId")
    def order_fk = foreignKey("order_fk", orderId, order)(_.id)

    def * = (id, orderId) <> ((Cart.apply _).tupled, Cart.unapply)
  }

  protected val cart = TableQuery[CartTable]
}
