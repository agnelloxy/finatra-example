name := "finatra-example"
organization := "com.github.agnelloxy"
version := "0.0.1"
scalaVersion := "2.12.1"

lazy val versions = new {
  val finatra = "2.12.0"
  val guice = "4.0"
  val logback = "1.1.7"
  val slick = "3.2.1"
  val slickJoda = "2.3.0"
  val mysqljdbc = "5.1.37"
}

resolvers ++= Seq(
  Resolver.sonatypeRepo("releases"),
  "Twitter Maven" at "https://maven.twttr.com"
)

libraryDependencies ++= Seq(
  "com.twitter" %% "finatra-http" % versions.finatra,
  "com.twitter" %% "finatra-httpclient" % versions.finatra,
  "com.typesafe.slick" %% "slick" % versions.slick,
  "com.typesafe.slick" %% "slick-hikaricp" % versions.slick,
  "com.github.tototoshi" %% "slick-joda-mapper" % versions.slickJoda,
  "mysql" % "mysql-connector-java" % versions.mysqljdbc
)
