package com.github.agnelloxy.finatra.integration

import com.github.agnelloxy.finatra.infra.slick.SampleTransactionLogDAO
import com.github.agnelloxy.finatra.repository.SampleTransactionLogRepository
import com.github.agnelloxy.finatra.service.SampleTransactionLogGenerationService
import com.google.inject.Inject
import com.twitter.finatra.httpclient.HttpClient
import com.twitter.finatra.json.FinatraObjectMapper
import javax.inject.Named

import slick.basic.DatabaseConfig
import slick.jdbc.JdbcProfile

class SampleTransactionLogGenerationServiceImpl @Inject()(
  @Named("blockChain") val httpClient: HttpClient,
  val dbConfig: DatabaseConfig[JdbcProfile],
  val mapper: FinatraObjectMapper
  ) extends SampleTransactionLogGenerationService {
  val sampleTransactionLogRepository: SampleTransactionLogRepository = new SampleTransactionLogDAO(dbConfig)
}
