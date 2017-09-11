package com.github.agnelloxy.finatra.infra.http

import javax.inject.Inject

import com.twitter.finagle.http.Response
import com.twitter.finatra.httpclient.{HttpClient, RequestBuilder}
import com.twitter.finatra.json.FinatraObjectMapper
import com.twitter.util.Future

class BlockchainResource @Inject()(httpClient: HttpClient, mapper: FinatraObjectMapper) {

  val domain = "https://blockchain.info/ja/rawaddr/"

  def get(url: String): Future[Response] =
    httpClient.execute(RequestBuilder.get(url))

}
