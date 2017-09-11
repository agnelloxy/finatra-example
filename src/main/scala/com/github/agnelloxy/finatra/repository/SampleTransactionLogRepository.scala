package com.github.agnelloxy.finatra.repository

import com.github.agnelloxy.finatra.domain.{SampleTransactionLog, TransactionLogId}
import com.github.agnelloxy.finatra.repository.common.BaseRepository

trait SampleTransactionLogRepository extends BaseRepository {

  def findById(id: TransactionLogId): scala.concurrent.Future[Option[SampleTransactionLog]]

}
