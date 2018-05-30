package models.payment

import models.SlickRepository

trait PaymentTable {

  this: SlickRepository =>

  import driver.api._

  protected class PaymentTable(tag: Tag) extends Table[Payment](tag, "payment") {
    def id = column[Long]("id", O.PrimaryKey, O.AutoInc)
    def paymentType = column[String]("paymentType")

    def * = (id, paymentType) <> ((Payment.apply _).tupled, Payment.unapply)
  }

  protected val payment = TableQuery[PaymentTable]
}
