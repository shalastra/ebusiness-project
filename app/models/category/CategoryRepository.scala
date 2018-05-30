package models.category

import javax.inject.{Inject, Singleton}
import models.SlickRepository
import play.api.db.slick.DatabaseConfigProvider
import slick.jdbc.JdbcProfile

import scala.concurrent.Future

@Singleton
class CategoryRepository @Inject() (dbConfigProvider: DatabaseConfigProvider)
  extends SlickRepository with CategoryTable {
  val dbConfig = dbConfigProvider.get[JdbcProfile]

  import dbConfig._
  import profile.api._

  def create(name: String): Future[Category] = db.run {
    (category.map(c => c.name)
      returning category.map(_.id)
      into ((name, id) => Category(id, name))
      ) += (name)
  }

  def list(): Future[Seq[Category]] = db.run {
    category.result
  }
}
