package com.noelmarkham.scalacheck.joda

import org.joda.time._

import org.scalacheck.Gen
import org.scalacheck.Arbitrary.arbitrary

object JodaGen {

  val genYearsPeriod: Gen[Years] = Gen.choose(-292275054, 292278993).map(Years.ZERO.plus(_))
  val genMonthsPeriod: Gen[Months] = Gen.choose(-292275054, 292278993).map(Months.ZERO.plus(_))
  val genWeeksPeriod: Gen[Weeks] = arbitrary[Int].map(Weeks.ZERO.plus(_))
  val genDaysPeriod: Gen[Days] = arbitrary[Int].map(Days.ZERO.plus(_))
  val genHoursPeriod: Gen[Hours] = arbitrary[Int].map(Hours.ZERO.plus(_))
  val genMinutesPeriod: Gen[Minutes] = arbitrary[Int].map(Minutes.ZERO.plus(_))
  val genSecondsPeriod: Gen[Seconds] = arbitrary[Int].map(Seconds.ZERO.plus(_))

  val genPeriod: Gen[ReadablePeriod] = Gen.oneOf(
    genYearsPeriod,
    genMonthsPeriod,
    genWeeksPeriod,
    genDaysPeriod,
    genHoursPeriod,
    genMinutesPeriod,
    genSecondsPeriod
  )

  def genDateTimeForRange(dt: DateTime, p: ReadablePeriod): Gen[DateTime] = {
    val diffMillis = Math.abs(dt.plus(p).getMillis() - dt.getMillis())

    Gen.choose(-diffMillis, diffMillis).map(dt.plus(_))
  }
}

