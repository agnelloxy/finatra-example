package com.github.agnelloxy.finatra.service

import com.github.agnelloxy.finatra.domain.{BitcoinAddress, TransactionLogId}
import com.github.agnelloxy.finatra.service.common.BaseService
import com.twitter.finatra.httpclient.{HttpClient, RequestBuilder}
import com.github.agnelloxy.finatra.repository.SampleTransactionLogRepository
import com.twitter.finatra.json.FinatraObjectMapper
import com.twitter.util.Future
import com.twitter.finagle.http.Response

trait SampleTransactionLogGenerationService extends BaseService {

  val httpClient: HttpClient
  val sampleTransactionLogRepository:SampleTransactionLogRepository
  val mapper: FinatraObjectMapper

  def getTransactionLog(bitcoinAddress: BitcoinAddress): Future[Response] =
    httpClient.execute(RequestBuilder.get("/posts#com.typicode.jsonplaceholder.Http$"))

  def findById(id: TransactionLogId) =
    sampleTransactionLogRepository.findById(id)

}
