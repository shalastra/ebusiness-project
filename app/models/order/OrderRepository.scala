package models.order

import javax.inject.{Inject, Singleton}
import models.SlickRepository
import play.api.db.slick.DatabaseConfigProvider
import slick.jdbc.JdbcProfile

import scala.concurrent.ExecutionContext

@Singleton
class OrderRepository @Inject()(dbConfigProvider: DatabaseConfigProvider)(implicit ec: ExecutionContext)
  extends SlickRepository with OrderTable {
  override implicit protected final val dbConfig = dbConfigProvider.get[JdbcProfile]

  import dbConfig._
  import profile.api._
}
