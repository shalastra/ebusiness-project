package models.order

import models.SlickRepository
import models.payment.PaymentTable
import models.user.UserTable

trait OrderTable extends UserTable with PaymentTable {
  this: SlickRepository =>

  import driver.api._

  protected class OrderTable(tag: Tag) extends Table[Order](tag, "order") {
    def id = column[Long]("id", O.PrimaryKey, O.AutoInc)
    def orderDate = column[String]("orderDate")
    def userId = column[Long]("userId")
    def user_fk = foreignKey("user_fk", userId, user)(_.id)
    def paymentId = column[Long]("paymentId")
    def payment_fk = foreignKey("payment_fk", paymentId, payment)(_.id)


    def * = (id, orderDate, userId, paymentId) <> ((Order.apply _).tupled, Order.unapply)
  }

  protected val order = TableQuery[OrderTable]
}
