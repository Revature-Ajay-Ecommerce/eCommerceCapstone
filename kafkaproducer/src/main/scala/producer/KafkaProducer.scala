import java.util.Properties
import org.apache.kafka.clients.producer._

object Producer {

  def main_p(args: Array[String]): Unit = {
    writeToKafka("quick-start")
  }

  def writeToKafka(topic: String): Unit = {
    val props = new Properties()
    //props.put("bootstrap.servers", "localhost:9094")
    props.put("bootstrap.servers", "sandbox-hdp.hortonworks.com:6667")
    props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer")
    props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer")
    val producer = new KafkaProducer[String, String](props)
    val record = new ProducerRecord[String, String](topic, "key", "value")
    producer.send(record)
    producer.close()
  }
  def dataGenerator(customerInfo: Tuple4[String, String, String, Int], orderInfo: Tuple8[String, String, Float, Int, Int, String, String, Int], timestamp: String, success: Char, reason: String): String = { Tuple16[Int, Int, String, Int, String, String, String, Int, Float, String, String, String, String, Int, Char, String)
        val orderID = orderInfo._8.toString
        val customerID = customerInfo._4.toString
        val customerName = customerInfo._1
        val productID = orderInfo._4.toString
        val productName = orderInfo._2
        val productCategory = orderInfo._1
        val paymentType = orderInfo._6
        val qty = orderInfo._5.toString
        val price = orderInfo._3    // In the return statemetn %.2f gives you tailing zeros
        val datetime = timestamp
        val country = customerInfo._3
        val city = customerInfo._2
        val website = orderInfo._7
        val transactionID = currentTransactionID.toString
        val transactionSuccess = success
        val failureReason = reason
}
