package com.github.agnelloxy.finatra.controller

import javax.inject.{Inject, Singleton}

import com.github.agnelloxy.finatra.domain.{BitcoinAddress, TransactionLogId}
import com.github.agnelloxy.finatra.integration.SampleTransactionLogGenerationServiceImpl
import com.twitter.finagle.http._
import com.twitter.finatra.http.Controller

import scala.concurrent.ExecutionContext.Implicits.global

case class ExampleCaseClass(id: Option[Int], name: String)

@Singleton
class ApplicationController @Inject()(
  service: SampleTransactionLogGenerationServiceImpl) extends Controller {

  get("/") { request: Request =>
    "Hello, world!"
  }
  get("/foo") { request: Request =>
    ExampleCaseClass(Some(1), "Example Name")
  }
  get("/send") { request: Request =>
    val futureResult = service.getTransactionLog(BitcoinAddress("xxx"))
    futureResult map { result =>
      println(result.contentString)
      response.ok.body(result.statusCode)
    }
  }
  get("/get") { request: Request =>
    service.findById(TransactionLogId(1)) map { result =>
      response.ok.body(result)
    }
  }
}

