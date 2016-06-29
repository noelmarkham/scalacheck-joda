package com.noelmarkham.scalacheck.joda.granularity

import com.noelmarkham.scalacheck.joda.Granularities._

package object seconds {
  implicit val defaultGranularity: Granularity = Seconds
}
