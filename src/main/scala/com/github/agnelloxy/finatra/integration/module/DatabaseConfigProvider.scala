package com.github.agnelloxy.finatra.integration.module

import slick.basic.{BasicProfile, DatabaseConfig}

trait DatabaseConfigProvider[P <: BasicProfile] {
  protected val dbConfig: DatabaseConfig[P]
  protected final lazy val profile: P = dbConfig.profile
  protected final def db: P#Backend#Database = dbConfig.db
}
