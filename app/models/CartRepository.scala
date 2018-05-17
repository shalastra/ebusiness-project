package models

import javax.inject.{Inject, Singleton}
import play.api.db.slick.DatabaseConfigProvider
import slick.jdbc.JdbcProfile

import scala.concurrent.{ExecutionContext, Future}

@Singleton
class CartRepository @Inject() (dbConfigProvider: DatabaseConfigProvider)(implicit ec: ExecutionContext) {
  val dbConfig = dbConfigProvider.get[JdbcProfile]

  import dbConfig._
  import profile.api._

  class CartTable(tag: Tag) extends Table[Cart](tag, "cart") {
    def id = column[Long]("id", O.PrimaryKey, O.AutoInc)
    def date = column[String]("date")

    def * = (id, date) <> ((Cart.apply _).tupled, Cart.unapply)
  }

  val cart = TableQuery[CartTable]

  def create(date: String): Future[Cart] = db.run {
    (cart.map(c => (c.date))
      returning cart.map(_.id)
      into ((date, id) => Cart(id, date))
      ) += (date)
  }

  def list(): Future[Seq[Cart]] = db.run {
    cart.result
  }
}
