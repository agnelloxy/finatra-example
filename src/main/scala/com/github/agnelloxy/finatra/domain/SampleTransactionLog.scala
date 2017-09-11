package com.github.agnelloxy.finatra.domain

import com.github.agnelloxy.finatra.domain.common._

//@todo fix model to just "Transaction"
case class SampleTransactionLog(
  id: Option[TransactionLogId],
  totalReceived: Bitcoin,
  totalSent: Bitcoin
  //meta: EntityMeta = EntityMeta() //@todo add meta info
) extends BaseEntity[TransactionLogId]

case class TransactionLogId(value: Long) extends BaseEntityId
