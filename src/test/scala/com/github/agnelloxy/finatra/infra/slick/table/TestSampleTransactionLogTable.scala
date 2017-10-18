package com.github.agnelloxy.finatra.infra.slick.table

import scala.concurrent.{ExecutionContext, Future}
import com.github.agnelloxy.finatra.domain.{Bitcoin, SampleTransactionLog, TransactionLogId}
import com.github.agnelloxy.finatra.infra.slick.common._

trait TestSampleTransactionLogTable extends TestCommonTableManager
  with SampleTransactionLogTable {

  import profile.api._
  implicit val ec: ExecutionContext

  val sampleTransactionLogFixtures = Seq(SampleTransactionLog(None, Bitcoin(3), Bitcoin(1)))
  val sampleTransactionLogFixturesWithIndex = sampleTransactionLogFixtures.zipWithIndex
    .map(r => r._1.copy(id = Some(TransactionLogId(r._2 + 1))))

  override def initTable(f: Future[Unit]) = {
    val initF = db.run(
      DBIO.seq(
        sampleTransactionLogs.schema.create,
        sampleTransactionLogs ++= sampleTransactionLogFixtures
      )
    ) flatMap(_ => f)
    super.initTable(initF)
  }

  override def terminateTable(f: Future[Unit]) = {
    val terminateF = db.run(
      sampleTransactionLogs.schema.drop
    ) flatMap(_ => f)
    super.initTable(terminateF)
  }

}

