package com.github.agnelloxy.finatra.integration

import com.github.agnelloxy.finatra.infra.slick.SampleTransactionLogDAO
import com.github.agnelloxy.finatra.integration.module.SlickMysqlModule
import com.github.agnelloxy.finatra.repository.SampleTransactionLogRepository
import com.github.agnelloxy.finatra.service.SampleTransactionLogGenerationService
import com.google.inject.Inject
import com.twitter.finatra.httpclient.HttpClient
import com.twitter.finatra.json.FinatraObjectMapper

class SampleTransactionLogGenerationServiceImpl @Inject()(
  val httpClient: HttpClient,
  val db: SlickMysqlModule.db,
  val mapper: FinatraObjectMapper
  ) extends SampleTransactionLogGenerationService {
  val sampleTransactionLogRepository: SampleTransactionLogRepository = new SampleTransactionLogDAO(db)
}
