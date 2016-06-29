package com.noelmarkham.scalacheck.joda

import org.joda.time._

import org.scalacheck.Gen
import org.scalacheck.Arbitrary
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

  def genDateTimeForRange(dt: DateTime, p: ReadablePeriod)(implicit g: Granularities.Granularity): Gen[DateTime] = {
    val diffMillis = Math.abs(dt.plus(p).getMillis() - dt.getMillis())

    Gen.choose(-diffMillis, diffMillis).map(millis => g.normalise(dt.plus(millis)))
  }
}

object Granularities {

  sealed trait Granularity { def normalise(dt: DateTime): DateTime }

  case object Millis extends Granularity {
    def normalise(dt: DateTime): DateTime = {
      dt
    }
  }

  case object Seconds extends Granularity {
    def normalise(dt: DateTime): DateTime = {
      dt.withMillisOfSecond(0)
    }
  }
  case object Minutes extends Granularity {
    def normalise(dt: DateTime): DateTime = {
      dt.withMillisOfSecond(0).withSecondOfMinute(0)
    }
  }

  case object Hours extends Granularity {
    def normalise(dt: DateTime): DateTime = {
      dt.withMillisOfSecond(0).withSecondOfMinute(0).withMinuteOfHour(0)
    }
  }

  case object Days extends Granularity {
    def normalise(dt: DateTime): DateTime = {
      dt.withMillisOfSecond(0).withSecondOfMinute(0).withMinuteOfHour(0).withHourOfDay(0)
    }
  }
  case object Months extends Granularity {
    def normalise(dt: DateTime): DateTime = {
      dt.withMillisOfSecond(0).withSecondOfMinute(0).withMinuteOfHour(0).withHourOfDay(0).withDayOfMonth(1)
    }
  }

  case object Years extends Granularity {
    def normalise(dt: DateTime): DateTime = {
      dt.withMillisOfSecond(0).withSecondOfMinute(0).withMinuteOfHour(0).withHourOfDay(0).withDayOfYear(1)
    }
  }

}


object JodaArb {

  import Granularities._
  implicit def arbDateTime(implicit g: Granularity): Arbitrary[DateTime] = Arbitrary {
    arbitrary[Long].map { l =>
      g.normalise(new DateTime(l))
    }
  }

}
