package com.github.agnelloxy.finatra

import com.twitter.finatra.http.Controller
import com.twitter.finagle.http.{Request, Response}
import com.twitter.finatra.httpclient.{HttpClient, RequestBuilder}
import com.twitter.util.Future
import javax.inject.{Inject, Singleton}

import com.google.inject.Provides
import com.typesafe.config.{Config, ConfigFactory}
import com.twitter.finatra.json.FinatraObjectMapper
import com.twitter.finatra.httpclient.modules.HttpClientModule
import com.twitter.inject.TwitterModule
import com.github.tototoshi.slick.MySQLJodaSupport._
import scala.concurrent.ExecutionContext.Implicits.global //@todo fix better

case class ExampleCaseClass(id: Option[Int], name: String)

@Singleton
class ApplicationController @Inject()(
  httpService: HttpClientService, dbService: SlickConnectingService) extends Controller {

  get("/") { request: Request =>
    "Hello, world!"
  }
  get("/foo") { request: Request =>
    ExampleCaseClass(Some(1), "Example Name")
  }
  get("/send") { request: Request =>
    val futureResult = httpService.get
    futureResult map { result =>
      response.ok.body(result.statusCode)
    }
  }
  get("/get") { request: Request =>
    dbService.findById(1) map { result =>
      response.ok.body(result)
    }
  }
}

class HttpClientService @Inject()(httpClient: HttpClient, mapper: FinatraObjectMapper) {

  def get: Future[Response] =
    httpClient.execute(RequestBuilder.get("http://www.google.com"))

}

object MyHttpClientModule extends HttpClientModule {

  val host = "www.example.com"
  val port = 80

  override val dest = s"$host:$port"
  override def defaultHeaders: Map[String, String] = Map("Host" -> host)

}

case class Company(id: Option[Int], name: String)

class SlickConnectingService @Inject()(db: SlickMysqlModule.db) {
  import driver.api._

  class CompanyTable(tag: Tag) extends Table[Company](tag, "companies") {

    def id = column[Int]("id", O.PrimaryKey, O.AutoInc)
    def name = column[String]("name")
    def * = (id.?, name) <> (Company.tupled, Company.unapply)
  }

  def companies = TableQuery[CompanyTable]

  def findById(id: Int): scala.concurrent.Future[Option[Company]] = {
    db.run(companies.filter(_.id === id).result.headOption)
  }

}

object SlickMysqlModule extends TwitterModule {
  import slick.jdbc.MySQLProfile.api._
  type db = slick.jdbc.MySQLProfile.api.Database

  @Singleton @Provides
  def provideDatabaseConfig(config: Config): db = Database.forConfig("slick.db", config)
}

object TypesafeConfigModule extends TwitterModule {

  val mode = flag("mode", "dev", "application run mode [dev:default, alpha, sandbox, beta, real]")

  val configPath = "conf/"

  private lazy val config = {
    logger info s"LOADING CONFIG FROM: ${mode()}"
    ConfigFactory load (configPath + mode())
  }

  @Provides @Singleton
  def provideConfig(): Config = config
}
