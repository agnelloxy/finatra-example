package com.github.agnelloxy.finatra

import com.twitter.finatra.http.Controller
import com.twitter.finagle.http.{Request, Response}

import com.twitter.finatra.httpclient.{HttpClient, RequestBuilder}
import com.twitter.util.Future
import javax.inject.{Inject, Singleton}
import com.twitter.finatra.json.FinatraObjectMapper
import com.twitter.finatra.httpclient.modules.HttpClientModule

case class ExampleCaseClass(id: Option[Int], name: String)

@Singleton
class ApplicationController @Inject()(service: HttpClientService) extends Controller {

  get("/") { request: Request =>
    "Hello, world!"
  }
  get("/foo") { request: Request =>
    ExampleCaseClass(Some(1), "Example Name")
  }
  get("/send") { request: Request =>
    val futureResult = service.get
    futureResult map { result =>
      response.ok.body(result.statusCode)
    }
  }
}

class HttpClientService @Inject()(httpClient: HttpClient, mapper: FinatraObjectMapper) {

  def get: Future[Response] =
    httpClient.execute(RequestBuilder.get("http://www.google.com"))

}

object MyHttpClientModule extends HttpClientModule {

  val host = "www.fakeresponse.com"
  val port = 80

  override val dest = s"$host:$port"
  override def defaultHeaders: Map[String, String] = Map("Host" -> host)

}
