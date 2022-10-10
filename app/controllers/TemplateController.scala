package controllers

import models.TemplateResponse
import services.TemplateService
import play.api.libs.json.Json
import play.api.mvc.{AbstractController, Action, AnyContent, ControllerComponents, Result}

import scala.concurrent.ExecutionContext

class TemplateController(
  templateService: TemplateService,
  cc: ControllerComponents
)(implicit
  ec: ExecutionContext
) extends AbstractController(cc) {

  def getStatus(): Action[AnyContent] =
    Action.async {
      templateService.getStatus.map { status =>
        Ok(
          Json.toJson(
            TemplateResponse(
              url = "www.google.com",
              status = status
            )
          )
        )
      }
    }

  def getTemplates(): Action[AnyContent] =
    Action.async {
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
