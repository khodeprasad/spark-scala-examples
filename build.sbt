name := "spark-scala-examples"
version := "1.0"
scalaVersion := "2.11.8"

com.github.retronym.SbtOneJar.oneJarSettings

updateOptions := updateOptions.value.withCachedResolution(true)

libraryDependencies += "org.scala-lang" % "scala-compiler" % "2.11.8"
libraryDependencies += "org.scala-lang" % "scala-reflect" % "2.11.8"
libraryDependencies += "org.scala-lang.modules" % "scala-parser-combinators_2.11" % "1.0.4"
libraryDependencies += "org.scala-lang.modules" % "scala-xml_2.11" % "1.0.4"

libraryDependencies += "org.apache.spark" % "spark-core_2.11" % "2.1.0"
libraryDependencies += "org.apache.spark" % "spark-sql_2.11" % "2.1.0"

libraryDependencies += "com.stratio.datasource" % "spark-mongodb_2.11" % "0.12.0"
dependencyOverrides += "org.mongodb" % "casbah-query_2.11" % "3.1.1"
dependencyOverrides += "org.mongodb" % "casbah-core_2.11" % "3.1.1"
