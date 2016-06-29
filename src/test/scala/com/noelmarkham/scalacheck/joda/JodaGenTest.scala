package com.noelmarkham.scalacheck.joda

import org.joda.time._
import org.joda.time.format.PeriodFormat

import org.scalacheck._
import org.scalacheck.Prop._
import org.scalacheck.Gen._
import org.scalacheck.Arbitrary.arbitrary

import JodaGen._

object JodaGenTest extends Properties("Joda Generators") {

  property("genDateTimeForRange should only be generated within a specified period") = forAll(genPeriod) { p =>

    val now = new DateTime()

    forAll(genDateTimeForRange(now, p)) { generated =>

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
