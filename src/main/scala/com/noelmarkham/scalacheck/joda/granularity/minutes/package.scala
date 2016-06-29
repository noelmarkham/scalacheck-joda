package com.noelmarkham.scalacheck.joda.granularity

import com.noelmarkham.scalacheck.joda.Granularities._

package object minutes {
  implicit val defaultGranularity: Granularity = Minutes
}
