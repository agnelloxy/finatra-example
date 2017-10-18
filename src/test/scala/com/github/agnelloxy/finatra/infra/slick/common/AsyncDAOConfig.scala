package com.github.agnelloxy.finatra.infra.slick.common

import scala.concurrent.ExecutionContext

trait AsyncDAOConfig {
  implicit val ec: ExecutionContext = scala.concurrent.ExecutionContext.Implicits.global
}
