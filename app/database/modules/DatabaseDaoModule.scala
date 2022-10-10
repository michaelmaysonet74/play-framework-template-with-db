package database.modules

import com.softwaremill.macwire.wire
import database.dao.{TemplateDao, TemplateDaoImpl}
import slick.basic.DatabaseConfig
import slick.jdbc.PostgresProfile

import scala.concurrent.ExecutionContext

trait DatabaseDaoModule extends DatabaseTableModule {

  implicit def ec: ExecutionContext

  def dbConfig: DatabaseConfig[PostgresProfile]

  lazy val templateDAO: TemplateDao = wire[TemplateDaoImpl]

}
