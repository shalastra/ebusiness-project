package controllers

import javax.inject.Inject

import com.mohiva.play.silhouette.api.actions.SecuredRequest
import com.mohiva.play.silhouette.api.{ LogoutEvent, Silhouette }
import org.webjars.play.WebJarsUtil
import play.api.i18n.I18nSupport
import play.api.mvc.{ AbstractController, AnyContent, ControllerComponents }
import utils.auth.DefaultEnv
import play.api.libs.json.Json
import util.Success

import models.user.UserRepository

import scala.concurrent.{ ExecutionContext, Future }

/**
  * The basic application controller.
  *
  * @param components  The Play controller components.
  * @param silhouette  The Silhouette stack.
  * @param webJarsUtil The webjar util.
  * @param assets      The Play assets finder.
  */
class ApplicationController @Inject() (
                                        components: ControllerComponents,
                                        userRepository: UserRepository,
                                        silhouette: Silhouette[DefaultEnv]
                                      )(
                                        implicit
                                        webJarsUtil: WebJarsUtil,
                                        ec: ExecutionContext,
                                        assets: AssetsFinder
                                      ) extends AbstractController(components) with I18nSupport {

  def index = silhouette.SecuredAction.async {
    implicit request: SecuredRequest[DefaultEnv, AnyContent] =>
      
    val userID = request.identity.userID
    val fullName = request.identity.fullName
    val email = request.identity.email
    var token = request.authenticator.id

    userRepository.getUserByEmail(email).onComplete({
      case Success(user) =>
        if (user.isEmpty) {
          userRepository.create(fullName, email, token)
        }
    })

    Future.successful(Redirect("http://localhost:3000"))
  }

  def getCurrentUser = silhouette.SecuredAction.async {
    implicit request: SecuredRequest[DefaultEnv, AnyContent] =>

    val userID = request.identity.userID
    val fullName = request.identity.fullName
    val email = request.identity.email
    var token = request.authenticator.id

    var json = (Json.obj(
      "userID" -> userID,
      "email" -> email,
      "name" -> fullName,
      "token" -> token))

    println(json)

    Future.successful(Ok(json))

  }

  def signOut = silhouette.SecuredAction.async {
    implicit request: SecuredRequest[DefaultEnv, AnyContent] =>
    val result = Redirect(routes.ApplicationController.index())
    silhouette.env.eventBus.publish(LogoutEvent(request.identity, request))
    silhouette.env.authenticatorService.discard(request.authenticator, result)
  }
}