package models.user

import javax.inject.{Inject, Singleton}
import models.SlickRepository
import play.api.db.slick.DatabaseConfigProvider
import slick.jdbc.JdbcProfile

import scala.concurrent.{ExecutionContext, Future}

@Singleton
class UserRepository @Inject() (dbConfigProvider: DatabaseConfigProvider)(implicit ec: ExecutionContext)
  extends SlickRepository with UserTable {

  override implicit protected final val dbConfig = dbConfigProvider.get[JdbcProfile]

  import dbConfig._
  import profile.api._

  def create(username: String, password: String): Future[User] = db.run {
    (user.map(p => (p.username, p.password))
      returning user.map(_.id) into {
      case ((username, password), id) => User(id, username, password)
    }) += (username, password)
  }

  def list(): Future[Seq[User]] = db.run {
    user.result
  }
}