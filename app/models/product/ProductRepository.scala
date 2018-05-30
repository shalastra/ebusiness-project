package models.product

import javax.inject.{Inject, Singleton}
import models.SlickRepository
import play.api.db.slick.DatabaseConfigProvider
import slick.jdbc.JdbcProfile

import scala.concurrent.{ExecutionContext, Future}

@Singleton
class ProductRepository @Inject() (dbConfigProvider: DatabaseConfigProvider)(implicit ec: ExecutionContext)
  extends SlickRepository with ProductTable {

  protected val dbConfig = dbConfigProvider.get[JdbcProfile]

  import dbConfig._
  import profile.api._

  def create(name: String, description: String, categoryId: Int): Future[Product] = db.run {
    (product.map(p => (p.name, p.description, p.categoryId))
      returning product.map(_.id)
      into {case ((name, description, categoryId), id) => Product(id, name, description, categoryId)}
    ) += (name, description,categoryId)
  }

  def list(): Future[Seq[Product]] = db.run {
    product.result
  }
}
