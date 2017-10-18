package com.github.agnelloxy.finatra.infra.slick.common

import com.github.agnelloxy.finatra.integration.module.DatabaseConfigProvider
import com.typesafe.config.ConfigFactory
import slick.basic.DatabaseConfig
import slick.jdbc.JdbcProfile

trait TestSlickDBConfig extends DatabaseConfigProvider[JdbcProfile] {
  private lazy val config = {
    ConfigFactory load "conf/test.conf"
  }
  final lazy val dbConfig: DatabaseConfig[JdbcProfile] = DatabaseConfig.forConfig[JdbcProfile]("test_db", config)
}
