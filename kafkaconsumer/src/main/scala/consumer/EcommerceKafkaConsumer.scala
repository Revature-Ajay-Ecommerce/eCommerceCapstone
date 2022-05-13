// spark-submit --packages org.apache.spark:spark-sql-kafka-0-10_2.12:3.0.0 ecommerconsumer.jar
// spark-submit --packages org.apache.spark:spark-sql-kafka-0-10_2.12:3.0.0 ecommerconsumer.jar

package consumer

import java.util
import org.apache.kafka.clients.consumer.KafkaConsumer
import java.util.Properties
import scala.collection.JavaConverters._

import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.functions._
import org.apache.spark.sql.Column
import org.apache.spark.sql.types._
import org.apache.spark.sql.{Column, Dataset, Row, SparkSession}


object EcommerceKafkaConsumer {

  def main(args: Array[String]): Unit = {
    consumeFromKafka("insurance")
  }

  def consumeFromKafka(topic: String) = {
    // val props = new Properties()
    // props.put("bootstrap.servers", "sandbox-hdp.hortonworks.com:6667")
    // props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer")
    // props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer")
    // props.put("auto.offset.reset", "latest")
    // props.put("group.id", "consumer-group")

    // // create kafka consumer object
    // val consumer: KafkaConsumer[String, String] = new KafkaConsumer[String, String](props)
    // consumer.subscribe(util.Arrays.asList(topic))

    // // print from ecommerce topic
    // while (true) {
    //   val record = consumer.poll(1000).asScala
    //   for (data <- record.iterator)
    //     println(data.value())
    // }
    val spark = SparkSession.builder
      .appName("File Source")
      .config("spark.master", "local[*]")
      .getOrCreate()

      spark.sparkContext.setLogLevel("ERROR")

    // val schema = StructType(
    //       List(
    //         StructField("claim_id", StringType, true),
    //         StructField("customer_id", StringType, true),
    //         StructField("customer_name", StringType, true),
    //         StructField("Customer_age", StringType, true),
    //         StructField("agent_id", StringType, true),
    //         StructField("agent_name", StringType, true),
    //         StructField("claim_category", StringType, true),
    //         StructField("amount", StringType, true),
    //         StructField("reason", StringType, true),
    //         StructField("agent_rating", StringType, true),
    //         StructField("datetime", StringType, true),
    //         StructField("country", StringType, true),
    //         StructField("state", StringType, true),
    //         StructField("approval", StringType, true),
    //         StructField("reimbursement_id", StringType, true),
    //         StructField("failure_reason", StringType, true)
    //       )
    //     )

    // // define schema for dataframe 
    // val schema = StructType(
    //   List(
    //     StructField("order_id", StringType, true),
    //     StructField("customer_id", StringType, true),
    //     StructField("customer_name", StringType, true),
    //     StructField("product_id", StringType, true),
    //     StructField("product_name", StringType, true),
    //     StructField("product_category", StringType, true),
    //     StructField("payment_type", StringType, true),
    //     StructField("qty", StringType, true),
    //     StructField("price", StringType, true),
    //     StructField("datetime", StringType, true),
    //     StructField("country", StringType, true),
    //     StructField("city", StringType, true),
    //     StructField("ecommerce_website_name", StringType, true),
    //     StructField("payment_txn_id", StringType, true),
    //     StructField("payment_txn_success", StringType, true),
    //     StructField("failure_reason", StringType, true)
    //   )
    // )

    val initDF = spark.readStream
    .format("kafka")
    .option("kafka.bootstrap.servers", "localhost:9092")
    .option("subscribe", "insurance")
    .load()
    .select(col("value").cast("string"))
    // df.selectExpr("CAST(key AS STRING)", "CAST(value AS STRING)")
    //   .as[(String, String)]

    // Output to file sink: CSV.
    // File sink only supports append output mode.
    // Delete the folder first.
    println("Output to csv...")
    // val resultDf = initDF.select("order_id", "customer_id", "customer_name", "product_id", "product_name", "product_category", "payment_type", "qty", "price", "datetime", "country", "city", "ecommerce_website_name", "payment_txn_id", "payment_txn_success", "failure_reason")
    val resultDf = initDF.select("value")
    resultDf
      .writeStream
      .outputMode("append") // Filesink only support Append mode.
      .format("csv") // supports these formats : csv, json, orc, parquet
      .option("path", "file:///home/hdoop/output/filesink_output")
      .option("header", false)
      .option("checkpointLocation", "file:///home/hdoop/output/checkpoint/filesink_checkpoint")
      .start()
      .awaitTermination()
    
      
  
      // val allfiles = spark.read.option("header","false").csv("file:///home/maria_dev/output/filesink_output/part-*.csv")
      // allfiles.coalesce(1).write.format("csv").option("header", "false").save("file:///home/maria_dev/csv_output")
    
  }
}


// SparkStreamFileSource.scala


// Exception in thread "main" org.apache.spark.sql.AnalysisException: cannot resolve '`order_id`' given input columns: [value];;
// Exception in thread "main" org.apache.spark.sql.streaming.StreamingQueryException: CSV data source does not support binary data type. --> can't write binary to CSV?
