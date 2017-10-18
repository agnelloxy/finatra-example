package com.github.agnelloxy.finatra.infra.slick.common

import scala.concurrent.Future

trait TestCommonTable {
  def initTable(f: Future[Unit]): Future[Unit]

  def terminateTable(f: Future[Unit]): Future[Unit]
}
