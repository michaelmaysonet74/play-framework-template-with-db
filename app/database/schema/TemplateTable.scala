package database.schema

import database.models.TemplateRecord
import slick.jdbc.PostgresProfile.api._

class TemplateTable(
  tag: Tag
) extends Table[TemplateRecord](tag, "play_framwork_template") {

  def id = column[Long]("id", O.PrimaryKey)

  def url = column[String]("url")

  def status = column[String]("status")

  override def * = (id, url, status) <> (TemplateRecord.tupled, TemplateRecord.unapply)

}
