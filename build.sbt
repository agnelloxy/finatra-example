name := "finatra-example"
organization := "com.github.agnelloxy"
version := "0.0.1"
scalaVersion := "2.12.1"

lazy val versions = new {
  val finatra = "2.12.0"
  val guice = "4.0"
  val logback = "1.1.7"
}

resolvers ++= Seq(
  Resolver.sonatypeRepo("releases"),
  "Twitter Maven" at "https://maven.twttr.com"
)

libraryDependencies ++= Seq(
  "com.twitter" %% "finatra-http" % versions.finatra,
  "com.twitter" %% "finatra-httpclient" % versions.finatra)
