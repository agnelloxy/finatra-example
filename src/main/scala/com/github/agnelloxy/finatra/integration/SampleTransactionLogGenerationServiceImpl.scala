package com.github.agnelloxy.finatra.integration

import com.github.agnelloxy.finatra.repository.SampleTransactionLogRepository
import com.github.agnelloxy.finatra.service.SampleTransactionLogGenerationService
import com.google.inject.Inject
import com.twitter.finatra.httpclient.HttpClient
import com.twitter.finatra.json.FinatraObjectMapper
import javax.inject.Named

import com.github.agnelloxy.finatra.infra.slick.dao.SampleTransactionLogDAO
import slick.basic.DatabaseConfig
import slick.jdbc.JdbcProfile

class SampleTransactionLogGenerationServiceImpl @Inject()(
  @Named("blockChain") val httpClient: HttpClient,
  @Named("testdb") val dbConfig: DatabaseConfig[JdbcProfile],
  val mapper: FinatraObjectMapper
  ) extends SampleTransactionLogGenerationService {
  val sampleTransactionLogRepository: SampleTransactionLogRepository = new SampleTransactionLogDAO(dbConfig)
}
