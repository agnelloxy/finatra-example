package com.github.agnelloxy.finatra.infra.slick.mapper

import com.github.agnelloxy.finatra.domain.TransactionLogId
import com.github.agnelloxy.finatra.integration.module.DatabaseConfigProvider
import slick.jdbc.JdbcProfile

trait TransactionLogIdMapper extends DatabaseConfigProvider[JdbcProfile] {
  import profile.api._
  implicit def transactionLogIdMapper = MappedColumnType.base[TransactionLogId, Long](_.value, TransactionLogId)
}
