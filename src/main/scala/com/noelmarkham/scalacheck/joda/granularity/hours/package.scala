package com.noelmarkham.scalacheck.joda.granularity

import com.noelmarkham.scalacheck.joda.Granularities._

package object hours {
  implicit val defaultGranularity: Granularity = Hours
}
