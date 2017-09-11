package com.github.agnelloxy.finatra.domain.common

import org.joda.time.DateTime

trait BaseEntity[Id <:BaseEntityId] {
  val id: Option[Id]
  //val meta: EntityMeta //@todo add meta info
}

case class EntityMeta(
  created:    DateTime = new DateTime().withMillisOfSecond(0),
  modified:   DateTime = new DateTime().withMillisOfSecond(0),
  isDeleted:  Boolean = false
)
