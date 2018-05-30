package models.order

import javax.inject.{Inject, Singleton}
import models.SlickRepository
import play.api.db.slick.DatabaseConfigProvider
import slick.jdbc.JdbcProfile

import scala.concurrent.{ExecutionContext, Future}

@Singleton
class OrderRepository @Inject()(dbConfigProvider: DatabaseConfigProvider)(implicit ec: ExecutionContext)
  extends SlickRepository with OrderTable {
  override implicit protected final val dbConfig = dbConfigProvider.get[JdbcProfile]

  import dbConfig._
  import profile.api._

  def create(orderDate: String, userId: Long, paymentId: Long): Future[Order] = db.run {
    (order.map(o => (o.orderDate, o.userId, o.paymentId))
      returning order.map(_.id) into {
      case ((orderDate, userId, paymentId), id) => Order(id, orderDate, userId, paymentId)
    }) += (orderDate, userId, paymentId)
  }

  def list(): Future[Seq[Order]] = db.run {
    order.result
  }
}
