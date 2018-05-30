package models.cart

import javax.inject.{Inject, Singleton}
import models.SlickRepository
import play.api.db.slick.DatabaseConfigProvider
import slick.jdbc.JdbcProfile

import scala.concurrent.Future

@Singleton
class CartRepository @Inject()(dbConfigProvider: DatabaseConfigProvider) extends CartRepositoryTrait
  with SlickRepository with CartTable {

  override implicit protected final val dbConfig = dbConfigProvider.get[JdbcProfile]

  import dbConfig._
  import profile.api._

  override def create(id: Long, orderId: Long): Future[Cart] = db.run {
    (cart.map(c => c.orderId)
      returning cart.map(_.id)
      into ((orderId, id) => Cart(id, orderId))
      ) += (orderId)
  }
}
