name := "flink_parallel_io"

organization := "org.example"

version := "0.0.1"

scalaVersion := "2.11.8"

libraryDependencies ++= Seq (
  "org.apache.flink" %% "flink-scala" % "1.0.3",
  "org.apache.flink" %% "flink-streaming-scala" % "1.0.3",
  "org.apache.flink" %% "flink-clients" % "1.0.3"
)

// for debugging sbt problems
//logLevel := Level.Debug

scalacOptions += "-deprecation"

mainClass in assembly := Some("org.example.App")

run in Compile <<= Defaults.runTask(fullClasspath in Compile, mainClass in (Compile, run), runner in (Compile, run))

assemblyOption in assembly := (assemblyOption in assembly).value.copy(includeScala = false)
