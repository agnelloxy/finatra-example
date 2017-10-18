package com.github.agnelloxy.finatra.infra.slick.mapper

import com.github.agnelloxy.finatra.domain.Bitcoin
import com.github.agnelloxy.finatra.integration.module.DatabaseConfigProvider
import slick.jdbc.JdbcProfile

trait BitCoinMapper extends DatabaseConfigProvider[JdbcProfile] {
  import profile.api._
  implicit def bitcoinMapper = MappedColumnType.base[Bitcoin, Long](_.value, Bitcoin)
}
