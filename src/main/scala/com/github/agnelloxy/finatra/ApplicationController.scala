package com.github.agnelloxy.finatra

import com.twitter.finatra.http.Controller
import com.twitter.finagle.http.{Request, Response}

import com.twitter.finatra.httpclient.{HttpClient, RequestBuilder}
import com.twitter.util.Future
import javax.inject.{Inject, Singleton}
import com.twitter.inject.conversions.future._

case class ExampleCaseClass(id: Option[Int], name: String)

@Singleton
class ApplicationController @Inject()(service: Service) extends Controller {

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

@Singleton
class Service @Inject()(httpClient: HttpClient) {

  def get: Future[Response] = {
    val r = httpClient.execute(RequestBuilder.get("http://www.google.com"))
    r
  }

}
