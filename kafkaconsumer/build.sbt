import Dependencies._

ThisBuild / scalaVersion     := "2.13.6"
ThisBuild / version          := "0.1.0-SNAPSHOT"
ThisBuild / organization     := "com.ecommerce"
ThisBuild / organizationName := "ecommerce"
val sparkVersion = "2.3.0"

lazy val root = (project in file("."))
  .settings(
    name := "kafkaconsumer",
    libraryDependencies += scalaTest % Test
    ,libraryDependencies += "org.apache.spark" %% "spark-core" % "3.2.0"
    ,libraryDependencies += "org.apache.spark" %% "spark-sql" % "3.2.0"
    ,libraryDependencies += "org.apache.spark" %% "spark-hive" % "3.2.0"
    // ,libraryDependencies += "org.apache.hadoop" % "hadoop-client" % "2.7.0"
    //Kafka
    ,libraryDependencies += "org.apache.spark" %% "spark-sql-kafka-0-10" % "3.2.0"
    ,libraryDependencies += "org.apache.kafka" %% "kafka" % "3.1.0"
    // For Json.
     // ,libraryDependencies += "net.liftweb" %% "lift-json" % "3.5.0"
    
  )

// See https://www.scala-sbt.org/1.x/docs/Using-Sonatype.html for instructions on how to publish to Sonatype.
