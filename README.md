scalacheck-joda
====

A helper library for using Joda-Time with ScalaCheck.

Motivation
===

The library is to help with useful generation of Joda-Time's classes for use with ScalaCheck, giving more control than `arbitrary[Long].map(new DateTime(_))`:

  * The _granularity_ of the generated instances, for instance only generating `DateTime` classes to the nearest minute, and
  * The _period_ of the generated instances, for instance only generating `DateTime` classes six months before or after today's date, rather than comparing dates in such a wide range that it is not useful at all.

Todo
===

(By no means complete)

  [x] Control period for DateTime
  [ ] Ability to specify a different period in the past and the future
  [ ] Control granularity for DateTime
  [ ] Package structure
  [ ] Fill out todos for the other types (for both granularity and period where appropriate)