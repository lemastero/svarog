name := "svarog"

version := "0.0.1"

scalaVersion := "2.13.1"

resolvers += "bintray-scala-hedgehog" at "https://dl.bintray.com/hedgehogqa/scala-hedgehog"

val hedgehogVersion = "4d4763691024de171c6e10f6bd9aa996a174d296"
libraryDependencies ++= Seq(
  "eu.timepit" %% "refined" % "0.9.13",
  "org.typelevel" %% "simulacrum" % "1.0.0",
  "qa.hedgehog" %% "hedgehog-core" % hedgehogVersion % Test,
  "qa.hedgehog" %% "hedgehog-runner" % hedgehogVersion % Test,
  "qa.hedgehog" %% "hedgehog-sbt" % hedgehogVersion % Test
)

testFrameworks += TestFramework("hedgehog.sbt.Framework")

scalacOptions ++= Seq(
  "-encoding", "UTF-8",
  "-Ymacro-annotations"
)
