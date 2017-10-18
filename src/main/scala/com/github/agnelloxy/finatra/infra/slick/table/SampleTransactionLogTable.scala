package com.github.agnelloxy.finatra.infra.slick.table

import com.github.agnelloxy.finatra.domain._
import com.github.agnelloxy.finatra.integration.module.DatabaseConfigProvider
import slick.jdbc.JdbcProfile
import com.github.agnelloxy.finatra.infra.slick.mapper._

trait SampleTransactionLogTable
    extends DatabaseConfigProvider[JdbcProfile]
    with BitCoinMapper
    with TransactionLogIdMapper {
  import profile.api._

  class SampleTransactionLogTable(tag: Tag) extends Table[SampleTransactionLog](tag, "sample_transaction_logs") {

    def id = column[TransactionLogId]("id", O.PrimaryKey, O.AutoInc)
    def totalReceived = column[Bitcoin]("total_received")
    def totalSent     = column[Bitcoin]("total_sent")
    def * = (id.?, totalReceived, totalSent) <> (SampleTransactionLog.tupled, SampleTransactionLog.unapply)
  }

  def sampleTransactionLogs = TableQuery[SampleTransactionLogTable]

}
