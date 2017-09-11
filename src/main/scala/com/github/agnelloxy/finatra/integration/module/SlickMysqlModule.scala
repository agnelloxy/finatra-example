package com.github.agnelloxy.finatra.integration.module

import javax.inject.Singleton

import com.google.inject.Provides
import com.twitter.inject.TwitterModule
import com.typesafe.config.Config

object SlickMysqlModule extends TwitterModule {
  import slick.jdbc.MySQLProfile.api._
  type db = slick.jdbc.MySQLProfile.api.Database

  @Singleton @Provides
  def provideDatabaseConfig(config: Config): db = Database.forConfig("slick.db", config)
}
