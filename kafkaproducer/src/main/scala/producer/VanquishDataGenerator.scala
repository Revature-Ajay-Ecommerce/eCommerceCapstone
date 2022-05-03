package producer

import scala.util.Random
import java.io._
import scala.collection.mutable.ArrayBuffer

class AlchemyDataGenerator() {
    val orderGenerator = new Order()
    val customerGenerator = new Customer()

    var currentTransactionID = 1
    val failureReason = List("Invalid Information", "Payment Declined", "Transaction Error", "Insufficient Funds", "Connection Timeout")
    val minCustomers = 1500
    val maxCustomers = 2000
    val maxOrdersPerCustomer = 3
    
    def generateOrder(): Seq[String] = {
        val rad = new scala.util.Random
        val numberOfCustomers = minCustomers + rad.nextInt(maxCustomers - minCustomers)
        var order = ArrayBuffer[String]()

        val randomCustomer = customerGenerator.generateCustomerInfo()

        val randomOrder = orderGenerator.generateOrderInfo()

        val timestamp = generateDateTime()
        
        // Chance of payment success
        val success = {if (rad.nextFloat() > 0.75f) {'N'} else {'Y'}}
        val reason = {if(success == 'N') {generateRandomReason()} else {""}}

        // Loops for every product in the product vector from the order Tuple
        for(product <- randomOrder._1) {
            val currentOrder = Tuple8(product._1, product._2, product._3, product._4, product._5, randomOrder._2, randomOrder._3, randomOrder._4)
            order += generateRow(randomCustomer, currentOrder, timestamp, success, reason)
        }

        // Maybe don't need this, use paramter for transaction ID
        incrementTransactionID()
            
        return order
    }

    def incrementTransactionID(): Unit = {             
        currentTransactionID += 1
    }

    def generateDateTime(): String = {
        // This will need some major adjustment, maybe even its own class
        // Right now it just picks random numbers between two values, not smart
        // Maybe use datetime class for trends on certain days of the week
        // TODO: Make it so it has the correct number of days for each month
        // Look into java.time and java.util.Calendar
        // https://docs.oracle.com/javase/8/docs/api/java/time/package-summary.html
        // https://docs.oracle.com/javase/8/docs/api/java/util/Calendar.html
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

        // Rounds up to the minute
        return f"$randomYear-$randomMonth-$randomDay $randomHour%02d:$randomMinute%02d:00"
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
