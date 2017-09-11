package com.github.agnelloxy.finatra.integration.module

import com.twitter.finatra.httpclient.modules.HttpClientModule

object MyHttpClientModule extends HttpClientModule {

  val host = "www.fakeresponse.com"
  val port = 80

  override val dest = s"$host:$port"
  override def defaultHeaders: Map[String, String] = Map("Host" -> host)

}
