import Dependencies._

ThisBuild / scalaVersion     := "2.11.8"
ThisBuild / version          := "0.1.0-SNAPSHOT"
ThisBuild / organization     := "com.ecommerce"
ThisBuild / organizationName := "ecommerce"
val sparkVersion = "2.3.0"

lazy val root = (project in file("."))
  .settings(
    name := "kafkaproducer",
    libraryDependencies += scalaTest % Test
    ,libraryDependencies += "org.apache.spark" %% "spark-core" % "1.2.0"
    ,libraryDependencies += "org.apache.spark" %% "spark-sql" % "2.1.0"
    //,libraryDependencies += "org.apache.spark" %% "spark-streaming" % sparkVersion
    //,libraryDependencies += "org.apache.spark" %% "spark-core" % "2.4.3"
    //,libraryDependencies += "org.apache.spark" %% "spark-sql" % "2.4.3"
    // For kafka.
    ,libraryDependencies += "org.apache.spark" %% "spark-sql-kafka-0-10" % "2.3.4"
    ,libraryDependencies += "org.apache.kafka" %% "kafka" % "2.1.0"
  )

// See https://www.scala-sbt.org/1.x/docs/Using-Sonatype.html for instructions on how to publish to Sonatype.
