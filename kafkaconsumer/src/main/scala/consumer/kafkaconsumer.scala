package example

import java.util.{Collections, Properties}
import java.util.regex.Pattern
import org.apache.kafka.clients.consumer.KafkaConsumer
import scala.collection.JavaConverters._


import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.types._
import org.apache.spark.sql.functions._
import org.apache.spark.sql.{Column, Dataset, Row, SparkSession}

object consumer {
  def main(args: Array[String]): Unit = {
    val spark: SparkSession = SparkSession
      .builder()
      .master("local[4]")
      .appName("consumer")
      .getOrCreate()

      import spark.implicits._
   // spark.sparkContext.setLogLevel("ERROR")
   // val sc = spark.sparkContext

   val col1 = new StructType()
        .add("claim_id", StringType, true)
        .add("customer_id", StringType, true)
        .add("customer_name", StringType, true)
        .add("Customer_age", StringType, true)
        .add("agent_id", StringType, true)
        .add("agent_name", StringType, true)
        .add("claim_category", StringType, true)
        .add("amount", StringType, true)
        .add("reason", StringType, true)
        .add("agent_rating", StringType, true)
        .add("datetime", StringType, true)
        .add("country", StringType, true)
        .add("state", StringType, true)
        .add("approval", StringType, true)
        .add("reimbursement_id", StringType, true)
        .add("failure_reason", StringType, true)

    val df = spark.readStream
        .format("kafka")
        .option("kafka.bootstrap.servers", "sandbox-hdp.hortonworks.com:6667")
        .option("subscribe", "ecommerce")
        .load
        .select(col("value").cast("string"))

        val cdataframe = df.select(from_json(col("value"), col1))
        cdataframe.printSchema()

        cdataframe.writeStream.format("console").outputMode("append").option("truncate", "false").start().awaitTermination()
  }
}
