package com.github.agnelloxy.finatra.infra.slick

import com.github.agnelloxy.finatra.domain._
import com.github.agnelloxy.finatra.repository.SampleTransactionLogRepository
import com.github.agnelloxy.finatra.integration.module.DatabaseConfigProvider
import slick.basic.DatabaseConfig
import slick.jdbc.JdbcProfile

class SampleTransactionLogDAO (val dbConfig: DatabaseConfig[JdbcProfile])
  extends SampleTransactionLogRepository
  with DatabaseConfigProvider[JdbcProfile] {
  import profile.api._

  implicit def bitcoinMapper = MappedColumnType.base[Bitcoin, Long](_.value, Bitcoin)
  implicit def transactionLogIdMapper = MappedColumnType.base[TransactionLogId, Long](_.value, TransactionLogId)

  class SampleTransactionLogTable(tag: Tag) extends Table[SampleTransactionLog](tag, "sample_transaction_logs") {

    def id = column[TransactionLogId]("id", O.PrimaryKey, O.AutoInc)
    def totalReceived = column[Bitcoin]("total_received")
    def totalSent     = column[Bitcoin]("total_sent")
    def * = (id.?, totalReceived, totalSent) <> (SampleTransactionLog.tupled, SampleTransactionLog.unapply)
  }

  def companies = TableQuery[SampleTransactionLogTable]

  def findById(id: TransactionLogId): scala.concurrent.Future[Option[SampleTransactionLog]] = {
    db.run(companies.filter(_.id === id).result.headOption)
  }

}
