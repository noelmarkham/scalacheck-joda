name := "scalacheck-joda"

version := "0.1.0-SNAPSHOT"

scalaVersion := "2.11.7"

libraryDependencies ++= Seq(
  "org.scalacheck" %% "scalacheck" % "1.13.0",
  "joda-time" % "joda-time" % "2.9.4"
)

initialCommands in console := s"""import com.noelmarkham.scalacheck.joda._
                                 |import com.noelmarkham.scalacheck.joda.JodaArb._
                                 |import org.scalacheck.Arbitrary
                                 |import org.scalacheck.Arbitrary._
                                 |import org.joda.time._""".stripMargin
