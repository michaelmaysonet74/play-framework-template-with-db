package controllers

import models.TemplateResponse
import services.TemplateService
import play.api.Logger
import play.api.libs.json.Json
import play.api.mvc.{AbstractController, Action, AnyContent, ControllerComponents}

import scala.concurrent.ExecutionContext

class TemplateController(
  templateService: TemplateService,
  cc: ControllerComponents
)(
  implicit ec: ExecutionContext,
  implicit val logger: Logger
) extends AbstractController(cc) {

  def getStatus(url: String): Action[AnyContent] =
    Action.async {
      logger.info(s"GET /rest/status?url=$url")
      templateService.getStatus(url).map { status =>
        Ok(
          Json.toJson(
            TemplateResponse(
              url = url,
              status = status
            )
          )
        )
      }
    }

  def getTemplates: Action[AnyContent] =
    Action.async {
      logger.info("GET /rest/templates")
      templateService.getTemplates.map { templates =>
        Ok(
          Json.toJson(
            templates.map(template =>
              TemplateResponse(
                url = template.url,
                status = template.status
              )
            )
          )
        )
      }
    }

}
