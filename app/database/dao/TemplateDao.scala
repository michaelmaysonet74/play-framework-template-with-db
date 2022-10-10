package database.dao

import database.models.TemplateRecord
import database.schema.TemplateTable
import slick.basic.DatabaseConfig
import slick.jdbc.PostgresProfile
import slick.jdbc.PostgresProfile.api._

import scala.concurrent.{ExecutionContext, Future}

trait TemplateDao {

  def all: Future[Seq[TemplateRecord]]

}

class TemplateDaoImpl(
  dbConfig: DatabaseConfig[PostgresProfile],
  template: TableQuery[TemplateTable]
)(implicit
  ec: ExecutionContext
) extends TemplateDao {

  import dbConfig._

  def all: Future[Seq[TemplateRecord]] = db.run(template.result)

}
