package models

import javax.inject.{Inject, Singleton}
import play.api.db.slick.DatabaseConfigProvider
import slick.jdbc.JdbcProfile

import scala.concurrent.ExecutionContext

@Singleton
class CartRepository @Inject()(dbConfigProvider: DatabaseConfigProvider)(implicit ec: ExecutionContext) {
  protected val dbConfig = dbConfigProvider.get[JdbcProfile]

  import dbConfig._
  import profile.api._

  protected class CartTable(tag: Tag) extends Table[Cart](tag, "cart") {
    def id = column[Long]("id", O.PrimaryKey, O.AutoInc)
    def orderId = column[Long]("orderId")

    def * = (id, orderId) <> ((Cart.apply _).tupled, Cart.unapply)
  }

  protected val cartTable = TableQuery[CartTable]
}
