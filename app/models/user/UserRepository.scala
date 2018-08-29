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

  def create(username: Option[String], email: Option[String], token: String): Future[User] = db.run {
    (user.map(p => (p.username, p.email, p.token))
      returning user.map(_.id) into {
      case ((username, email, token), id) => User(id, username, email, token)
    }) += (username, email, token)
  }

  def list(): Future[Seq[User]] = db.run {
    user.result
  }

  def getUserByEmail(email: Option[String]): Future[Option[User]] = db.run {
    user.filter(_.email === email).result.headOption
  }
}