package com.github.agnelloxy.finatra.infra.slick.common

import scala.concurrent.{Await, Future}
import scala.concurrent.duration._

trait TestCommonTableManager extends TestCommonTable {

  def initTable(f: Future[Unit]) = Await.ready(f, 10.seconds)

  def terminateTable(f: Future[Unit]) = Await.ready(f, 10.seconds)

}
