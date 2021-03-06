package com.github.agnelloxy.finatra.integration.module

import javax.inject.{Named, Singleton}

import com.google.inject.Provides
import com.twitter.inject.TwitterModule
import com.typesafe.config.Config
import slick.basic.DatabaseConfig
import slick.jdbc.JdbcProfile

object DatabaseConfigModule extends TwitterModule {
  @Named("testdb") @Singleton @Provides
  def provideDatabaseConfig(config: Config): DatabaseConfig[JdbcProfile] = DatabaseConfig.forConfig[JdbcProfile]("testdb", config)
}
