lazy val commonSettings = Seq(
  version := "0.1.0-SNAPSHOT",
  scalaVersion := "2.11.7"
)

/*
 * based on: https://github.com/spray/spray-template/blob/on_spray-can_1.3/build.sbt
 * but with with a more recent scala and specs2 version
 */

lazy val root = (project in file(".")).
  settings(commonSettings: _*).
  settings(
    name := "etag-server",
    libraryDependencies ++= {
      val akkaV = "2.3.9"
      val sprayV = "1.3.3"
      val kamonV = "0.6.0"
      Seq(
        "io.kamon"            %%  "kamon-core"    % kamonV,
        // [Optional]
//        "io.kamon"            %% "kamon-statsd"   % kamonV,
        // [Optional]
//        "io.kamon"            %% "kamon-datadog"  % kamonV,
        "io.spray"            %%  "spray-can"     % sprayV,
        "io.spray"            %%  "spray-routing" % sprayV,
//        "io.spray"            %%  "spray-json"    % "1.3.2",
        // -- json --
        "org.json4s"         %% "json4s-jackson" % "3.2.10",
        "io.spray"            %%  "spray-testkit" % sprayV  % "test",
        "com.typesafe.akka"   %%  "akka-actor"    % akkaV,
        "com.typesafe.akka"   %%  "akka-testkit"  % akkaV   % "test",
        "org.specs2"          %%  "specs2-core"   % "2.3.11" % "test"
      )
    }
  )

