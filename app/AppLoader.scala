package loader

import modules.TemplateModule
import com.softwaremill.macwire.wire
import play.api.ApplicationLoader
import play.api.ApplicationLoader.Context
import play.api.BuiltInComponentsFromContext
import play.filters.HttpFiltersComponents
import play.api.routing.Router
import router.Routes

import scala.concurrent.ExecutionContext

class AppLoader extends ApplicationLoader {

  override def load(context: Context) = new AppComponents(context).application

}

class AppComponents(
  context: Context
) extends BuiltInComponentsFromContext(context)
    with TemplateModule
    with HttpFiltersComponents {

  implicit lazy val ec: ExecutionContext = actorSystem.dispatcher

  lazy val router: Router = {
    lazy val prefix = "/"
    wire[Routes]
  }

}
