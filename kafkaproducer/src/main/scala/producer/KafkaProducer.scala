// create a topic using Amazon MSK (Managed Streaming for Apache Kafka): https://docs.aws.amazon.com/msk/latest/developerguide/create-topic.html
// produce and consume data: https://docs.aws.amazon.com/msk/latest/developerguide/produce-consume.html
// Class KafkaProducer<K,V> https://kafka.apache.org/11/javadoc/org/apache/kafka/clients/producer/KafkaProducer.html

// $KAFKA_HOME/bin/kafka-topics.sh --create --zookeeper localhost:2181 --replication-factor 1 --partitions 1 --topic ecommerce_records
// $KAFKA_HOME/bin/kafka-console-consumer.sh --bootstrap-server sandbox-hdp.hortonworks.com:6667 --topic ecommerce_records
// spark-submit --packages org.apache.spark:spark-sql-kafka-0-10_2.11:2.3.0,net.liftweb:lift-json_2.11:3.5.0 kafkaspark_2.11-0.1.0-SNAPSHOT.jar --class producer.KafkaProducer



// object KafkaProducer {
//   def main_kf(args: Array[String]): Unit = {
//     val props: Properties = new Properties()
//     // props.put("bootstrap.servers", "localhost:9092") // the EC2 Instance URL will go here
//     props.put(
//       "key.serializer",
//       "org.apache.kafka.common.serialization.StringSerializer"
//     )
//     props.put(
//       "value.serializer",
//       "org.apache.kafka.common.serialization.StringSerializer"
//     )
//     val producer =
//       new KafkaProducer[String, String](props) // creating a producer instance
//     val topic = "ecommerce_records"
//     try {
//       for (i <- 0 to 12500) {
//         println(s"Running cycle # $i...")
//         // val eCommerceData = ProducerGenerator.generateRecords(1000).toSeq

//         // implicit val formats = DefaultFormats //

//         shopData.map(rec => {
//           val jsonString = write(r) // writing records to file
//           println(jsonString)
//           val json = parse(jsonString) // parsing to json
//           val record =
//             new ProducerRecord[String, String](
//               topic,
//               jsonString
//             )
//           // creating a Producer record holding 1000 rows (takes topic, and jsonString as params)

//           val metadata =
//             producer.send(record) // sending Producer record to topic
//           printf(
//             record.key(),
//             record.value(),
//             metadata.get().partition(),
//             metadata.get().offset()
//           )
//         })

//         val ms = 10000
//         println(s"completed cycle # $i")
//         println(s"Going to sleep for $ms milliseconds...")
//         Thread.sleep(ms) // suspend execution of current thread for 5 seconds
//       }
//     } catch {
//       case e: Exception => e.printStackTrace()
//     } finally {
//       producer.close()
//     }
//   }
// }


// props √
// command to create topic (test on VM, then that command should work on EC2) √
// research sending messages to kafka topic on another PC/EC2 instance √
    //https://docs.aws.amazon.com/msk/latest/developerguide/mkc-create-topic.html
// touch base with Drake regarding EC2 instance √

// $KAFKA_HOME/bin/kafka-topics.sh --create --zookeeper localhost:2181 --replication-factor 1 --partitions 1 --topic ecommerce_records
// $KAFKA_HOME/bin/kafka-console-consumer.sh --bootstrap-server sandbox-hdp.hortonworks.com:6667 --topic ecommerce_records
// spark-submit 

// Create a Kafka Cluster Using AWS MSK And Stream Data - Full Coding Demo
// https://www.youtube.com/watch?v=y4wowEQd4Os&t=58s

package producer

import java.util.Properties
import org.apache.kafka.clients.producer.{KafkaProducer, ProducerRecord}
import org.apache.spark.sql.SparkSession


object KafkaProducer {
    
    val vDataGenerator = new VanquishDataGenerator()
    val aDataGenerator = new AlchemyDataGenerator()

  def main(args: Array[String]): Unit = {
    // props
    val props = new Properties() // construct empty property list
    props.put("bootstrap.servers", "<EC2_instance_URL>")
    prop.setProperty(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,"<EC2 Instance>")
    props.put()

    val producer = new KafkaProducer[String, String](props)
    val topic = "ecommerce_records"

    def GenerateRecords(): Unit = {
      try {
        for (i <- 1 to 12500) {
          println(s"Running cycle # $i...")
          vRow = vDataGenerator.generateData()
          aRow = aDataGenerator.generateData()

          val vRecord = new ProducerRecord[String, String](topic,vRow)
          val aRecord = new ProducerRecord[String, String](topic, aRow)

          val vMetadata =
            producer.send(vRecord) // sending Producer record to topic
          printf(
            vRecord.key(),
            vRecord.value(),
            metadata.get().partition(),
            metadata.get().offset()
          )
          val aMetadata =
              producer.send(aRecord) // sending Producer record to topic
            printf(
            aRecord.key(),
            aRecord.value(),
            metadata.get().partition(),
            metadata.get().offset()
          )
        }
      } catch {
          case e: Exception => e.printStackTrace()
      } finally {
          producer.close()
      }
    }
  }
}
