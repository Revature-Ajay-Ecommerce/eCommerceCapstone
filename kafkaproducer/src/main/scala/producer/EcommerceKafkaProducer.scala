// $KAFKA_HOME/bin/kafka-topics.sh --create --zookeeper localhost:2181 --replication-factor 1 --partitions 1 --topic ecommerce
// $KAFKA_HOME/bin/kafka-console-consumer.sh --bootstrap-server sandbox-hdp.hortonworks.com:6667 --topic ecommerce
// spark-submit --packages org.apache.spark:spark-sql-kafka-0-10_2.11:2.3.0 kafkaspark_2.11-0.1.0-SNAPSHOT.jar


// props √
// command to create topic (test on VM, then that command should work on EC2) √
// research sending messages to kafka topic on another PC/EC2 instance √
    //https://docs.aws.amazon.com/msk/latest/developerguide/mkc-create-topic.html
// touch base with Drake regarding EC2 instance √

// Create a Kafka Cluster Using AWS MSK And Stream Data - Full Coding Demo
// https://www.youtube.com/watch?v=y4wowEQd4Os&t=58s

package producer

import java.util.Properties
import org.apache.kafka.clients.producer.{KafkaProducer, ProducerRecord}
import org.apache.spark.sql.SparkSession

object EcommerceKafkaProducer {
    val vDataGenerator = new VanquishDataGenerator()
    //val aDataGenerator = new AlchemyDataGenerator()

    // props
    val props = new Properties() // construct empty property list
    //props.put("bootstrap.servers", "<EC2_instance_URL>")
    //props.setProperty(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,"<EC2 Instance>")
    //props.put()
    props.put("bootstrap.servers", "sandbox-hdp.hortonworks.com:6667")
    props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer")
    props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer")

    // The acks config controls the criteria under which requests are considered complete.
    // The "all" setting we have specified will result in blocking on the full commit of the record,
    // the slowest but most durable setting.
    props.put("acks", "all")

    val producer = new KafkaProducer[String, String](props)
    val topic = "ecommerce"

    def main(args: Array[String]): Unit = {
        generateRecords()
    }
    
    def generateRecords(): Unit = {
        try {
            for (i <- 1 to 12500) {
                println(s"Running cycle # $i...")
                val vRow = vDataGenerator.generateOrder()
                //val aRow = aDataGenerator.generateData()

                val vRecord = new ProducerRecord[String, String](topic,vRow)
                //val aRecord = new ProducerRecord[String, String](topic, aRow)

                val vMetadata = producer.send(vRecord) // sending Producer record to topic
                    
                printf(s"sent record(key=%s value=%s) " +
                    "meta(partition=%d, offset=%d)\n",
                    vRecord.key(),
                    vRecord.value(),
                    vMetadata.get().partition(),
                    vMetadata.get().offset()
                )
                // val aMetadata =
                //     producer.send(aRecord) // sending Producer record to topic
                //   printf(
                //   aRecord.key(),
                //   aRecord.value(),
                //   aMetadata.get().partition(),
                //   aMetadata.get().offset()
                // )
            }
        }
        catch {
            case e: Exception => e.printStackTrace()
        } 
        finally {
            producer.close()
        }
    }
}
