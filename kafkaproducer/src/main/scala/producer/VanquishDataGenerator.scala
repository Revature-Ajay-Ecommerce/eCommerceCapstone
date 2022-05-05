package producer

import scala.util.Random
import java.io._
import scala.collection.mutable.ArrayBuffer

class VanquishDataGenerator() {
    val orderGenerator = new Order()
    val customerGenerator = new Customer()
    val timestampGenerator = new DateTimeGenerator()

    // At this point it would be more work to refactor this into its own class...
    var currentTransactionID = 2
    val failureReason = List("Invalid Information", "Payment Declined", "Transaction Error", "Insufficient Funds", "Connection Timeout")
    
    def generateOrder(): String = {
        val rad = new scala.util.Random

        val randomCustomer = customerGenerator.generateCustomerInfo(rad.nextDouble())

        val randomOrder = orderGenerator.generateOrderInfo()

        val timestamp = timestampGenerator.generateDateTime()
        
        // Chance of payment success
        val success = {if (rad.nextFloat() > 0.75f) {'N'} else {'Y'}}
        val reason = {if(success == 'N') {generateRandomReason()} else {""}}

        val order = generateRow(randomCustomer, randomOrder, timestamp, success, reason)

        incrementTransactionID()
        
        return order
    }

    def incrementTransactionID(): Unit = {             
        currentTransactionID += 2
    }

    def generateRandomReason(): String = {
        return failureReason(Random.nextInt(failureReason.length))
    }

    def generateRow(customerInfo: Tuple4[String, String, String, Int], orderInfo: Tuple8[String, String, Float, Int, Int, String, String, Int], timestamp: String, success: Char, reason: String): String = {
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
