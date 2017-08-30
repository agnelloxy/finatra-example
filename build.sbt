name := "finatra-example"
organization := "com.github.agnelloxy"
version := "0.0.1"
scalaVersion := "2.12.1"

resolvers +=
  "Twitter" at "http://maven.twttr.com"

libraryDependencies += "com.twitter" %% "finatra-http" % "2.12.0"
libraryDependencies += "com.twitter" %% "finatra-http" % "2.12.0" % "test" classifier "tests"

