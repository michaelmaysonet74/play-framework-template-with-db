package services

import clients.TemplateClient
import dao.TemplateDAO
import dao.{models => daoModels}
import models.Template

import scala.concurrent.{ExecutionContext, Future}

trait TemplateService {

  def getStatus: Future[String]

  def getTemplates: Future[Seq[Template]]

}

class TemplateServiceImpl(
  templateClient: TemplateClient,
  templateDAO: TemplateDAO
)(implicit
  ec: ExecutionContext
) extends TemplateService {

  override def getStatus: Future[String] = templateClient.getGoogleStatus

  override def getTemplates: Future[Seq[Template]] =
    templateDAO.all.map(_.map(mapToTemplate))

  private def mapToTemplate(template: daoModels.Template): Template = Template(
    url = template.url,
    status = template.status
  )
}
