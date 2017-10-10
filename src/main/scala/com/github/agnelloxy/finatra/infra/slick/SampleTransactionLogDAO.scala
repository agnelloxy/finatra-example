package com.github.agnelloxy.finatra.infra.slick

import com.github.agnelloxy.finatra.domain._
import com.github.agnelloxy.finatra.repository.SampleTransactionLogRepository
import slick.basic.DatabaseConfig
import slick.jdbc.JdbcProfile
import com.github.agnelloxy.finatra.infra.slick.table.SampleTransactionLogTable

class SampleTransactionLogDAO(val dbConfig: DatabaseConfig[JdbcProfile])
  extends SampleTransactionLogDAOLike

trait SampleTransactionLogDAOLike
  extends SampleTransactionLogTable
  with SampleTransactionLogRepository {
  import profile.api._

  def findById(id: TransactionLogId): scala.concurrent.Future[Option[SampleTransactionLog]] = {
    db.run(companies.filter(_.id === id).result.headOption)
  }

}
