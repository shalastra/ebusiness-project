package models.review

import javax.inject.{Inject, Singleton}
import models.SlickRepository
import play.api.db.slick.DatabaseConfigProvider
import slick.jdbc.JdbcProfile

import scala.concurrent.{ExecutionContext, Future}

@Singleton
class ReviewRepository @Inject() (dbConfigProvider: DatabaseConfigProvider)(implicit ec: ExecutionContext)
  extends SlickRepository with ReviewTable {

  override implicit protected final val dbConfig = dbConfigProvider.get[JdbcProfile]

  import dbConfig._
  import profile.api._

  def create(grade: Int, comment: String, productId: Long): Future[Review] = db.run {
    (review.map(r => (r.grade, r.comment, r.productId))
      returning review.map(_.id) into {
      case ((grade, comment, productId), id) => Review(id, grade, comment, productId)
    }
      ) += (grade, comment, productId)
  }

  def list(): Future[Seq[Review]] = db.run {
    review.result
  }
}
