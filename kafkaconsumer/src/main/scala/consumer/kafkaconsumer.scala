package consumer

import java.util.{Collections, Properties}
import java.util.regex.Pattern
import org.apache.kafka.clients.consumer.KafkaConsumer
import scala.collection.JavaConverters._


import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.types._
import org.apache.spark.sql.functions._
import org.apache.spark.sql.{Column, Dataset, Row, SparkSession}

object consumer {
  // def main(args: Array[String]): Unit = {
  //   val spark: SparkSession = SparkSession
  //     .builder()
  //     .master("local[*]")
  //     .appName("consumer")
  //     .getOrCreate()

  //     import spark.implicits._
  //  // spark.sparkContext.setLogLevel("ERROR")
  //  // val sc = spark.sparkContext

  //  val schema = new StructType()
  //       .add("claim_id", StringType, true)
  //       .add("customer_id", StringType, true)
  //       .add("customer_name", StringType, true)
  //       .add("customer_age", StringType, true)
  //       .add("agent_id", StringType, true)
  //       .add("agent_name", StringType, true)
  //       .add("claim_category", StringType, true)
  //       .add("amount", StringType, true)
  //       .add("reason", StringType, true)
  //       .add("agent_rating", StringType, true)
  //       .add("datetime", StringType, true)
  //       .add("country", StringType, true)
  //       .add("state", StringType, true)
  //       .add("approval", StringType, true)
  //       .add("reimbursement_id", StringType, true)
  //       .add("failure_reason", StringType, true)

  //   val df1 = spark.readStream.format("kafka").option("kafka.bootstrap.servers", "sandbox-hdp.hortonworks.com:6667").option("subscribe", "insurance").load().select(col("value").cast("string"))

  //   val df2 = df1.selectExpr("CAST (value AS STRING)").toDF("value")

  //   val df3 = df2.select(from_json(col("value"), schema))
  //   df3.printSchema()

  //   df3.writeStream.format("console").outputMode("append").option("truncate", "false").start().awaitTermination()

  //   //df3.write.csv("~/insuranceOut.csv")

  // }

  def main(args: Array[String]):Unit = {
        val props: Properties = new Properties()
        props.put("group.id", "test-consumer-group") //need group id from
        props.put("bootstrap.servers", "sandbox-hdp.hortonworks.com:6667")
        props.put(
        "key.deserializer",
        "org.apache.kafka.common.serialization.StringDeserializer"
        )
        props.put(
        "value.deserializer",
        "org.apache.kafka.common.serialization.StringDeserializer"
        )
        props.put("enable.auto.commit", "true")
        props.put("auto.commit.interval.ms", "2000")
        val consumer = new KafkaConsumer(props)
        val topics = List("insurance")
        try {
        consumer.subscribe(topics.asJava) //subscribe to topic as a type Java
        while (true) {
            val records = consumer.poll(10) //read one record every 10 seconds
            for (record <- records.asScala) { // convert the read records into Scala code
              println( //for each record print Topic, Key, Value, Offset, Partiton
                // "Topic: " + record.topic() +
                ",Key: " + record.key() +
                ",Value: " + record.value() +
                // ", Offset: " + record.offset() +
                ", Partition: " + record.partition()
              )
            }
        }
        } catch {
        case e: Exception => e.printStackTrace()
        } finally {
        consumer.close()
        }
    }
}
