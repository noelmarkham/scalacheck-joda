package com.noelmarkham.scalacheck.joda.granularity

import com.noelmarkham.scalacheck.joda.Granularities._

package object days {
  implicit val defaultGranularity: Granularity = Days
}
