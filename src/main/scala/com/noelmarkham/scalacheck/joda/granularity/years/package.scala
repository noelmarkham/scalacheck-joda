package com.noelmarkham.scalacheck.joda.granularity

import com.noelmarkham.scalacheck.joda.Granularities._

package object years {
  implicit val defaultGranularity: Granularity = Years
}
