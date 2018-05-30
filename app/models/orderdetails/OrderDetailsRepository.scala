package models.orderdetails

import javax.inject.{Inject, Singleton}
import models.SlickRepository
import play.api.db.slick.DatabaseConfigProvider
import slick.jdbc.JdbcProfile

import scala.concurrent.{ExecutionContext, Future}

@Singleton
class OrderDetailsRepository @Inject() (dbConfigProvider: DatabaseConfigProvider)
                                       (implicit ec: ExecutionContext)
  extends SlickRepository with OrderDetailsTable {

  override implicit protected final val dbConfig = dbConfigProvider.get[JdbcProfile]

  import dbConfig._
  import profile.api._

  def create(orderDate: String, productPrice: Double,
             quantity: Int, totalPrice: Double, orderId: Long,
             productId: Long): Future[OrderDetails] = db.run {
    (orderDetails.map( od => (od.orderDate, od.productPrice,
      od.quantity, od.totalPrice, od.orderId, od.productId))
      returning order.map(_.id) into {
      case ((orderDate, productPrice, quantity, totalPrice, orderId, productId),id) =>
        OrderDetails(id, orderDate, productPrice, quantity, totalPrice, orderId, productId)
    }) += (orderDate, productPrice, quantity, totalPrice, orderId, productId)
  }

  def list(): Future[Seq[OrderDetails]] = db.run {
    orderDetails.result
  }
}