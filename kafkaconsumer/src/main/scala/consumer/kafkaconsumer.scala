package example
import org.apache.spark.sql.SparkSession
import org.apache.kafka.clients.consumer.KafkaConsumer
import org.apache.spark.sql.types._
import java.io._

object consumer {
  def main(args: Array[String]): Unit = {
    val spark: SparkSession = SparkSession
      .builder()
      .master("local[4]")
      .appName("consumer")
      .getOrCreate()
   // spark.sparkContext.setLogLevel("ERROR")
    val sc = spark.sparkContext

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
        .format("csv")
        .put(BOOTSTRAP_SERVERS_CONFIG, "ec2-3-93-174-172.compute-1.amazonaws.com:9092")
        .put("subscribe", "ecommerce")
        .load
        .select(col("value").cast("string"))

        val copydataframe = df.select(from_json(col("value"), schema))
        cdataframe.printSchema()

        cdataframe.writeStream
          outputMode("update")
          .format("console")
          .start()
          .awaitTermination()
  }
}
