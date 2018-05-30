package models.order

import javax.inject.{Inject, Singleton}
import play.api.db.slick.DatabaseConfigProvider
import slick.jdbc.JdbcProfile

import scala.concurrent.ExecutionContext

@Singleton
class OrderRepository @Inject()(dbConfigProvider: DatabaseConfigProvider)(implicit ec: ExecutionContext) {
  protected val dbConfig = dbConfigProvider.get[JdbcProfile]

  import dbConfig._
  import profile.api._

  protected class OrderTable(tag: Tag) extends Table[Order](tag, "order") {
    def id = column[Long]("id", O.PrimaryKey, O.AutoInc)
    def orderDate = column[String]("orderDate")
    def userId = column[Long]("userId")
    def paymentId = column[Long]("paymentId")

    def * = (id, orderDate, userId, paymentId) <> ((Order.apply _).tupled, Order.unapply)
  }

  protected val orderTable = TableQuery[OrderTable]
}
