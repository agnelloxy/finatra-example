package com.github.agnelloxy.finatra

import com.github.agnelloxy.finatra.controller.ApplicationController
import com.twitter.finagle.http.{Request, Response}
import com.twitter.finatra.http.HttpServer
import com.twitter.finatra.http.filters.{CommonFilters, LoggingMDCFilter, TraceIdMDCFilter}
import com.twitter.finatra.http.routing.HttpRouter
import com.github.agnelloxy.finatra.integration.module._

object ApplicationServerMain extends ApplicationServer

class ApplicationServer extends HttpServer {

  override def modules = Seq(TypesafeConfigModule, SlickMysqlModule) ++  HttpClientModules.modules

  override val defaultFinatraHttpPort = ":9000"
  override val defaultHttpPort = 9001 //??

  override def configureHttp(router: HttpRouter) {
    router
      .filter[LoggingMDCFilter[Request, Response]]
      .filter[TraceIdMDCFilter[Request, Response]]
      .filter[CommonFilters]
      .add[ApplicationController]
  }
}
