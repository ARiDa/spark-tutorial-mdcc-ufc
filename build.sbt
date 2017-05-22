name := "spark-tutorial-ufc"

version := "1.0"

scalaVersion := "2.11.4"

// https://mvnrepository.com/artifact/org.apache.spark/spark-core_2.10
libraryDependencies += "org.apache.spark" %% "spark-core" % "2.1.1" % "compile" // % "provided"

libraryDependencies += "com.fasterxml.jackson.core" % "jackson-core" % "2.7.2"
libraryDependencies += "com.fasterxml.jackson.core" % "jackson-annotations" % "2.7.2"
libraryDependencies += "com.fasterxml.jackson.core" % "jackson-databind" % "2.7.2"
libraryDependencies += "com.fasterxml.jackson.module" %% "jackson-module-scala" % "2.7.2"
libraryDependencies += "com.typesafe.play" %% "play-json" % "2.5.10"

javacOptions in Compile ++= Seq(
  "-source", "1.7",
  "-target", "1.7",
  "-Xlint:unchecked",
  "-Xlint:deprecation"
)
