package loader

import modules.TemplateModule
import akka.stream._
import com.softwaremill.macwire.wire
import play.api.ApplicationLoader.Context
import play.api.{ApplicationLoader, BuiltInComponentsFromContext}
import play.api.routing.Router
import play.api.libs.ws.ahc.StandaloneAhcWSClient
import play.filters.HttpFiltersComponents
import play.shaded.ahc.org.asynchttpclient.{DefaultAsyncHttpClient, DefaultAsyncHttpClientConfig}
import router.Routes
import slick.jdbc.PostgresProfile.api._

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

  val db = Database.forConfig("postgres")

  implicit lazy val wsClient: StandaloneAhcWSClient = {
    implicit val materializer: Materializer = SystemMaterializer(actorSystem).materializer

    val asyncHttpClientConfig = new DefaultAsyncHttpClientConfig.Builder()
      .setMaxRequestRetry(0)
      .setShutdownQuietPeriod(0)
      .setShutdownTimeout(0)
      .build

    lazy val asyncHttpClient: DefaultAsyncHttpClient = wire[DefaultAsyncHttpClient]

    wire[StandaloneAhcWSClient]
  }

  lazy val router: Router = {
    lazy val prefix = "/"
    wire[Routes]
  }

}
