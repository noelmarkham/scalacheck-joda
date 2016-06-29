package com.noelmarkham.scalacheck.joda

import org.joda.time._
import org.joda.time.format.PeriodFormat

// todo remove wildcards
import org.scalacheck._
import org.scalacheck.Prop._
import org.scalacheck.Gen._
import org.scalacheck.Arbitrary.arbitrary

//import TimeUnits._

import DateTimeRange._

object ScalacheckJodaHelpers {

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
}

object DateTimeRangeTest extends Properties("DateTime") {

  property("Should only be generated within a specified period") = forAll(ScalacheckJodaHelpers.genPeriod) { p =>

    val now = new DateTime()

    forAll(genDateTimeRange(now, p)) { generated =>

      val maxBoundary = now.plus(p)
      val minBoundary = now.minus(p)

      val resultText = s"""Period:       ${PeriodFormat.getDefault().print(p)}
                          |Now:          $now
                          |Generated:    $generated
                          |Min boundary: $minBoundary
                          |Max Boundary: $maxBoundary""".stripMargin

      val check = if(minBoundary == maxBoundary) { // period is zero
        generated == minBoundary
      } else if(minBoundary.isBefore(now)) { // period is positive
        minBoundary.isBefore(generated) && maxBoundary.isAfter(generated)
      } else { // period is negative
        maxBoundary.isBefore(generated) && minBoundary.isAfter(generated)
      }

      resultText |: check
    }
  }
}
