package controllers

import org.webjars.play.WebJarsUtil
import play.api.i18n.{I18nSupport, Messages}
import play.api.mvc.{AbstractController, AnyContent, ControllerComponents}
import play.api.libs.json.Json.toJson

import scala.concurrent.{ExecutionContext, Future}
import javax.inject.Inject
import play.api.Logger
import play.api.libs.json.{JsResult, Json}
import javax.inject._
import models._
import play.api.data.Form
import play.api.data.Forms._
import play.api.data.validation.Constraints._
import play.api.i18n._
import play.api.libs.json.Json
import play.api.mvc._

import scala.collection.mutable.ArrayBuffer

class ApplicationController @Inject()(
                                       components: ControllerComponents
                                     )(
                                       implicit
                                       webJarsUtil: WebJarsUtil,
                                       assets: AssetsFinder,
                                       ec: ExecutionContext
                                     ) extends AbstractController(components) with I18nSupport {

  def index = Action.async { implicit request =>
    Future.successful(Redirect("http://localhost:3000/"))
  }
}

