package modules

import com.softwaremill.macwire.wire
import dao.TemplateDAO
import slick.jdbc.PostgresProfile

import scala.concurrent.ExecutionContext

trait TemplateDAOModule {

  implicit def ec: ExecutionContext

  def db: PostgresProfile.backend.Database

  lazy val templateDAO: TemplateDAO = wire[TemplateDAO]

}
