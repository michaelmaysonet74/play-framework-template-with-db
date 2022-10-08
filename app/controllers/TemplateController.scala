package controllers

import models.TemplateModel
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

  def get(): Action[AnyContent] =
    Action.async {
      templateService.getText.map { text =>
        Ok(
          Json.toJson(TemplateModel(text))
        )
      }
    }

}
