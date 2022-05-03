// Run on VM as: spark-submit ./producer_2.11-0.1.0-SNAPSHOT.jar  --class ecommerce.Ecommerce
package ecommerce

import scala.util.Random
import ecommerce.{Order, Customer, HiveClient}
import java.io._
import scala.collection.mutable.ArrayBuffer

object Ecommerce {
    val orderGenerator = new Order()
    val customerGenerator = new Customer()
    val hive = new HiveClient()
    var currentTransactionID = 1
    val failureReason = List("Invalid Card Number", "Payment Declined", "Transaction Error", "Insufficient Funds")
    val minCustomers = 1500
    val maxCustomers = 2000
    val maxOrdersPerCustomer = 3
    val csvPath = "/home/maria_dev/ecommerce/vanquish/vanquishData.csv"
    val hdfsPath = "hdfs://sandbox-hdp.hortonworks.com:8020/user/maria_dev/vanquish/vanquishData.csv"
    
    def main(args: Array[String]) {
        val rad = new scala.util.Random
        val numberOfCustomers = minCustomers + rad.nextInt(maxCustomers - minCustomers)
        var rowsOfData = ArrayBuffer[String]()

        println("Generating Data...")
        for(i <- 0 to numberOfCustomers) {
            val numberOfOrders = rad.nextInt(maxOrdersPerCustomer)
            val randomCustomer = customerGenerator.generateCustomerInfo()

            for(i <- 0 to numberOfOrders) {
                val randomOrder = orderGenerator.generateOrderInfo()
                val timestamp = generateDateTime()
                // Random chance of success
                val success = {if (rad.nextFloat() > 0.75f) {'N'} else {'Y'}}
                val reason = {if(success == 'N') {generateRandomReason()} else {""}}

                // Loops for every product in the product vector from the order Tuple
                for(product <- randomOrder._1) {
                    val currentOrder = Tuple8(product._1, product._2, product._3, product._4, product._5, randomOrder._2, randomOrder._3, randomOrder._4)
                    rowsOfData += generateRow(randomCustomer, currentOrder, timestamp, success, reason)
                }

                incTransactionID()
            }
        }

        println("Writing Data to CSV...")
        appendRowsToCSV(rowsOfData)

        println(s"Uploading $csvPath to HDFS...")
        hive.loadIntoHDFS(csvPath, hdfsPath)

        println("Loading into Hive...")
        hive.loadIntoHive("/user/maria_dev/vanquish/")
    }

    def appendRowsToCSV(rows: ArrayBuffer[String]): Unit = {
        val file = new File(csvPath)
        val bw = new BufferedWriter(new FileWriter(file))

        for(row <- rows) {
            bw.write(row+"\n")
        }
        bw.close()
    }

    def incTransactionID(): Unit = {             
        currentTransactionID += 1
    }

    def generateDateTime(): String = {
        val r =  new scala.util.Random
        val firstYear = 2000
        val lastYear = 2021
        val randomYear = firstYear + r.nextInt( (lastYear - firstYear))

        val firstMonth = 1
        val lastMonth = 12
        val randomMonth = firstMonth + r.nextInt( (lastMonth - firstMonth))

        val firstDay = 1
        val lastDay = 30
        val randomDay = firstDay + r.nextInt( (lastDay - firstDay))

        val firstHour = 0
        val lastHour = 23
        val randomHour = firstHour + r.nextInt( (lastHour - firstHour))

        val firstMinute = 0
        val lastMinute = 59
        val randomMinute = firstMinute + r.nextInt( (lastMinute - firstMinute))

        // SO, Hive only supports timestamps in the format YYYY-MM-DD HH:mm:SS and right now we don't have seconds so it doesn't recognize it as a timestamp data type, 
        // for now I'm just going to keep it as a type string on hive but if we want to use the timestamp format we have to add seonds position...
        // https://sparkbyexamples.com/apache-hive/hive-date-and-timestamp-functions-examples/
        // personally I blame whoever came up with the schema, but ehhh whatever
        // you just have to convert after the fact
        // val firstSecond = 0
        // val lastSecond = 59
        // val randomSecond = firstSecond + r.nextInt( (lastSecond - firstSecond))

        return f"$randomYear-$randomMonth-$randomDay $randomHour%02d:$randomMinute%02d"
    }

    def generateRandomReason(): String = {
        return failureReason(Random.nextInt(failureReason.length))
    }

    def generateRow(customerInfo: Tuple4[String, String, String, Int], orderInfo: Tuple8[String, String, Float, Int, Int, String, String, Int], timestamp: String, success: Char, reason: String): String = { //Tuple16[Int, Int, String, Int, String, String, String, Int, Float, String, String, String, String, Int, Char, String] = {
        // In some ways this sucks, in other ways it will make it much clearer what each index is.
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
        
        return f"$orderID,$customerID,$customerName,$productID,$productName,$productCategory,$paymentType,$qty,$price%.2f,$datetime,$country,$city,$website,$transactionID,$transactionSuccess,$failureReason" 
    }
}
