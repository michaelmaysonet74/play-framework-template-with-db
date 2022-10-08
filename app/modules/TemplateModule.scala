package modules

import controllers.TemplateController
import services.{TemplateService, TemplateServiceImpl}
import com.softwaremill.macwire.wire
import play.api.mvc.ControllerComponents

import scala.concurrent.ExecutionContext

trait TemplateModule {

  implicit def ec: ExecutionContext

  def controllerComponents: ControllerComponents

  lazy val templateService: TemplateService = wire[TemplateServiceImpl]
  lazy val templateController: TemplateController = wire[TemplateController]

}
