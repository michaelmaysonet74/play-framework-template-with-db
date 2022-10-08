package services

import scala.concurrent.{ExecutionContext, Future}

trait TemplateService {

  def getText: Future[String]

}

class TemplateServiceImpl()(implicit
  ec: ExecutionContext
) extends TemplateService {

  override def getText: Future[String] = Future.successful("Hello, Template")

}
