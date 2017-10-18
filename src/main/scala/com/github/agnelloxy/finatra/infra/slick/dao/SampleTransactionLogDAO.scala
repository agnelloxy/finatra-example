package com.github.agnelloxy.finatra.infra.slick.dao

import com.github.agnelloxy.finatra.domain._
import com.github.agnelloxy.finatra.infra.slick.table.SampleTransactionLogTable
import com.github.agnelloxy.finatra.repository.SampleTransactionLogRepository
import slick.basic.DatabaseConfig
import slick.jdbc.JdbcProfile

class SampleTransactionLogDAO(val dbConfig: DatabaseConfig[JdbcProfile])
  extends SampleTransactionLogDAOLike

trait SampleTransactionLogDAOLike
  extends SampleTransactionLogTable
  with SampleTransactionLogRepository {

  import profile.api._

  def findById(id: TransactionLogId): scala.concurrent.Future[Option[SampleTransactionLog]] = {
    db.run(sampleTransactionLogs.filter(_.id === id).result.headOption)
  }

}
