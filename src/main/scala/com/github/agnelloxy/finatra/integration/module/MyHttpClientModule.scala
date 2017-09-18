package com.github.agnelloxy.finatra.integration.module

import com.twitter.finatra.httpclient.modules.HttpClientModule

object MyHttpClientModule extends HttpClientModule {

  val host = "blockchain.info"
  val port = 443

  override val sslHostname = Some(host)
  override val dest = s"$host:$port"
  override def defaultHeaders: Map[String, String] = Map("Host" -> host)

}
