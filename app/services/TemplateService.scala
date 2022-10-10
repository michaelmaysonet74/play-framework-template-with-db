package services

import clients.TemplateClient
import database.dao.TemplateDao
import database.models.TemplateRecord
import models.Template

import scala.concurrent.{ExecutionContext, Future}

trait TemplateService {

  def getStatus: Future[String]

  def getTemplates: Future[Seq[Template]]

}

class TemplateServiceImpl(
  templateClient: TemplateClient,
  templateDao: TemplateDao
)(implicit
  ec: ExecutionContext
) extends TemplateService {

  override def getStatus: Future[String] = templateClient.getGoogleStatus

  override def getTemplates: Future[Seq[Template]] =
    templateDao.all.map(_.map(mapToTemplate))

  private def mapToTemplate(
    template: TemplateRecord
  ): Template = Template(
    url = template.url,
    status = template.status
  )

}
