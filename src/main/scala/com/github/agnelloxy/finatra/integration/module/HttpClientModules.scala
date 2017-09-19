package com.github.agnelloxy.finatra.integration.module

import com.twitter.finatra.httpclient.modules.HttpClientModule
import com.twitter.finatra.httpclient.HttpClient
import com.twitter.inject.TwitterModule
import com.twitter.finatra.json.FinatraObjectMapper
import javax.inject.{Named, Singleton}
import com.google.inject.Provides
import com.typesafe.config.Config

object HttpClientModules {
  val modules = Seq(BlockChainHttpClientModule())
}

abstract class BasicHttpClientModule() extends TwitterModule {

  protected def provideHttpClientFromClientName(mapper: FinatraObjectMapper, clientName: String, config: Config) = {
    val path = "httpClient." + clientName
    val specificConfig = config.getConfig(path)
    provideHttpClient(mapper,
      specificConfig.getString("host"),
      specificConfig.getInt("port"),
      specificConfig.getBoolean("ssl")
    )
  }

  protected def provideHttpClient(mapper: FinatraObjectMapper, host: String, port: Int = 80, ssl: Boolean = false): HttpClient = {
    val httpClientModule = new HttpClientModule {
      override def dest: String = s"$host:$port"
      override val sslHostname = if (ssl) Some(host) else None
      override def defaultHeaders: Map[String, String] = Map("Host" -> host)
    }
    httpClientModule.provideHttpClient(mapper, httpClientModule.provideHttpService)
  }
}

object BlockChainHttpClientModule {

  def apply() = new BasicHttpClientModule {
    @Named("blockChain") @Provides @Singleton
    def provideHttpClient(mapper: FinatraObjectMapper, config: Config) = {
      super.provideHttpClientFromClientName(mapper, "blockChain", config)
    }
  }
}
