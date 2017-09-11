package com.github.agnelloxy.finatra.integration.module

import javax.inject.Singleton
import com.google.inject.Provides
import com.twitter.inject.TwitterModule
import com.typesafe.config.{Config, ConfigFactory}

object TypesafeConfigModule extends TwitterModule {

  val mode = flag("mode", "dev", "application run mode [dev:default, alpha, sandbox, beta, real]")

  val configPath = "conf/"

  private lazy val config = {
    logger info s"LOADING CONFIG FROM: ${mode()}"
    ConfigFactory load (configPath + mode())
  }

  @Provides @Singleton
  def provideConfig(): Config = config
}
