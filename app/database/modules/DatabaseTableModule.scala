package database.modules

import com.softwaremill.macwire.wire
import database.schema.TemplateTable
import slick.lifted.TableQuery

trait DatabaseTableModule {

  lazy val template: TableQuery[TemplateTable] = TableQuery(_ => wire[TemplateTable])

}
