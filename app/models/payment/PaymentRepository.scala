package models.payment

import javax.inject.{Inject, Singleton}
import models.SlickRepository
import play.api.db.slick.DatabaseConfigProvider
import slick.jdbc.JdbcProfile

import scala.concurrent.{ExecutionContext, Future}

@Singleton
class PaymentRepository @Inject() (dbConfigProvider: DatabaseConfigProvider)(implicit ec: ExecutionContext)
  extends SlickRepository with PaymentTable {

  override implicit protected final val dbConfig = dbConfigProvider.get[JdbcProfile]

  import dbConfig._
  import profile.api._

  def create(paymentType: String): Future[Payment] = db.run {
    (payment.map(p => p.paymentType)
      returning payment.map(_.id) into ((paymentType, id) => Payment(id, paymentType))
      ) += (paymentType)
  }

  def list(): Future[Seq[Payment]] = db.run {
    payment.result
  }
}
