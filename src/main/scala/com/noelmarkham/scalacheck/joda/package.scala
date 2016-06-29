package com.noelmarkham.scalacheck

import com.noelmarkham.scalacheck.joda.Granularities._

package object joda {
  implicit val defaultGranularity: Granularity = Millis
}
