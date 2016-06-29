package com.noelmarkham.scalacheck.joda.granularity

import com.noelmarkham.scalacheck.joda.Granularities._

package object months {
  implicit val defaultGranularity: Granularity = Months
}
