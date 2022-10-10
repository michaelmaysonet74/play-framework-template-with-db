package modules

import clients.TemplateClient
import controllers.TemplateController
import services.{TemplateService, TemplateServiceImpl}
import com.softwaremill.macwire.wire
import database.modules.DatabaseModule
import play.api.Logger
import play.api.libs.ws.WSClient
import play.api.mvc.ControllerComponents

import scala.concurrent.ExecutionContext

trait TemplateModule extends DatabaseModule {

  implicit def ec: ExecutionContext
  implicit def logger: Logger

  def controllerComponents: ControllerComponents
  def wsClient: WSClient

  lazy val templateClient: TemplateClient = wire[TemplateClient]
  lazy val templateService: TemplateService = wire[TemplateServiceImpl]
  lazy val templateController: TemplateController = wire[TemplateController]

}
