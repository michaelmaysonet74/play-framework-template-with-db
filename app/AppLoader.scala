package loader

import modules.TemplateModule
import com.softwaremill.macwire.wire
import play.api.ApplicationLoader.Context
import play.api.{ApplicationLoader, BuiltInComponentsFromContext, Logger, LoggerConfigurator}
import play.api.routing.Router
import play.api.libs.ws.ahc.AhcWSComponents
import play.api.db.slick.{DbName, SlickComponents}
import play.filters.HttpFiltersComponents
import router.Routes
import slick.basic.DatabaseConfig
import slick.jdbc.PostgresProfile

import scala.concurrent.ExecutionContext

class AppLoader extends ApplicationLoader {

  override def load(context: Context) = {
    LoggerConfigurator(context.environment.classLoader).foreach {
      _.configure(context.environment, context.initialConfiguration, Map.empty)
    }
    new AppComponents(context).application
  }

}

class AppComponents(
  context: Context
) extends BuiltInComponentsFromContext(context)
    with TemplateModule
    with AhcWSComponents
    with HttpFiltersComponents
    with SlickComponents {

  implicit lazy val ec: ExecutionContext = actorSystem.dispatcher

  implicit lazy val logger: Logger = Logger("application")

  lazy val dbConfig: DatabaseConfig[PostgresProfile] =
    slickApi.dbConfig(DbName("default"))

  lazy val router: Router = {
    lazy val prefix = "/"
    wire[Routes]
  }

}
