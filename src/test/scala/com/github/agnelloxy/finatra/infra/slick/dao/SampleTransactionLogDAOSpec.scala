package com.github.agnelloxy.finatra.infra.slick.dao

import com.github.agnelloxy.finatra.domain.TransactionLogId
import com.github.agnelloxy.finatra.infra.slick.table.TestSampleTransactionLogTable
import scala.concurrent.ExecutionContext
import com.github.agnelloxy.finatra.infra.slick.common._

class TestSampleTransactionLogDAO
  extends TestSlickDBConfig
    with SampleTransactionLogDAOLike
    with TestSampleTransactionLogTable
    with AsyncDAOConfig

class SampleTransactionLogDAOSpec extends CommonDAOSpec {

  val dao = new TestSampleTransactionLogDAO
  override implicit val ec: ExecutionContext = dao.ec

  "SampleTransactionLogDAOSpec" should {

    "findById" in {
      val f = dao.findById(TransactionLogId(1))
      val maybeResult = dao.sampleTransactionLogFixturesWithIndex.headOption
      whenReady(f) { result =>
        maybeResult match {
          case Some(r) => result shouldBe Some(r)
          case _ => fail("result not found")
        }
      }
    }
  }
}
