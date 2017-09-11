package com.github.agnelloxy.finatra.domain.common

trait BaseEntityId {
  val value: Long
  require(value > 0)

  override def toString = value.toString
}
