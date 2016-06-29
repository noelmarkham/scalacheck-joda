package com.noelmarkham.scalacheck.joda

import org.joda.time.DateTime
import org.joda.time.ReadablePeriod

import org.scalacheck.Gen

trait JodaGen {

  def genDateTimeForRange(dt: DateTime, p: ReadablePeriod): Gen[DateTime] = {
    val diffMillis = Math.abs(dt.plus(p).getMillis() - dt.getMillis())

    Gen.choose(-diffMillis, diffMillis).map(dt.plus(_))
  }
}

object JodaGen extends JodaGen

