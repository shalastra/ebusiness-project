package models

import javax.inject.{Inject, Singleton}
import play.api.db.slick.DatabaseConfigProvider
import slick.jdbc.JdbcProfile

@Singleton
class ProductRepository @Inject() (dbConfigProvider: DatabaseConfigProvider, categoryRepository: CategoryRepository)(implicit ec: ExecutionContext) {
  private val dbConfig = dbConfigProvider.get[JdbcProfile]

  import dbConfig._
  import profile.api._

  private class ProductTable(tag: Tag) extends Table[Product](tag, "product") {

    def id = column[Long]("id", O.PrimaryKey, O.AutoInc)
    def name = column[String]("name")
    def description = column[String]("description")
    def category = column[Int]("category")
    def category_fk = foreignKey("cat_fk",category, cat)(_.id)

    def * = (id, name, description, category) <> ((Product.apply _).tupled, Product.unapply)
  }

  import categoryRepository.CategoryTable

  private val product = TableQuery[ProductTable]

  private val cat = TableQuery[CategoryTable]

  def create(name: String, description: String, category: Int): Future[Product] = db.run {
    (product.map(p => (p.name, p.description,p.category))
      returning product.map(_.id)
      into {case ((name,description,category),id) => Product(id,name, description,category)}
    ) += (name, description,category)
  }

  def list(): Future[Seq[Product]] = db.run {
    product.result
  }
}
