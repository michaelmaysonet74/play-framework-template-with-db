package dao

import dao.models.Template
import slick.jdbc.PostgresProfile
import slick.jdbc.PostgresProfile.api._

import scala.concurrent.{ExecutionContext, Future}

class TemplateDAO(
  db: PostgresProfile.backend.Database
)(implicit ec: ExecutionContext) {
  private val Templates = TableQuery[TemplatesTable]

  def all: Future[Seq[Template]] = db.run(Templates.result)

  private class TemplatesTable(tag: Tag) extends Table[Template](tag, "playframeworktemplate") {
    def id = column[Long]("id", O.PrimaryKey)

    def url = column[String]("url")

    def status = column[String]("status")

    override def * = (id, url, status) <> (Template.tupled, Template.unapply)
  }

}
