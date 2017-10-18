package com.github.agnelloxy.finatra.infra.slick.common

import org.scalatest.{BeforeAndAfter, Matchers, WordSpec}
import org.scalatest.concurrent.ScalaFutures
import org.scalatest.time.{Millis, Seconds, Span}

import scala.concurrent.{ExecutionContext, Future}

trait CommonDAOSpec extends WordSpec
  with ScalaFutures
  with Matchers
  with BeforeAndAfter {

  val dao: TestCommonTable
  implicit val ec: ExecutionContext
  implicit val defaultPatience = PatienceConfig(timeout = Span(10, Seconds), interval = Span(500, Millis))

  before {
    dao.initTable(Future.successful())
  }

  after {
    dao.terminateTable(Future.successful(()))
  }
}
