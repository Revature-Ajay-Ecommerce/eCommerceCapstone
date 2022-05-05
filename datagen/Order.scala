package datagen

import scala.util.Random
import datagen.Customer
import datagen.Product

class Order (id: Int, datetime_ts: String){

  // var count = 1

  // def getOrderId(): Unit = {
  //   count = count + 1
  // }

  def getRandomWebsite(): String = {

    val websiteList = Vector("www.Amazon.com", "www.BestBuy.com", "www.Walmart.com", "www.Ebay.com", "www.Etsy.com")
    val random = new Random
    val website = websiteList(random.nextInt(websiteList.length))
    return website
  }

  def getRandomQty(): String = {
    val qtyList = Vector("1", "2", "3")
    val random = new Random
    val qty = qtyList(random.nextInt(qtyList.length))
    return qty
  }

  def getRandomTxStatus(): Char = {

    val random = new Random
    val success = {if (random.nextFloat() > 0.75f) {'N'} else {'Y'}}
    return success
  }

  def generateRandomReason(success: Char): String = {
    if (success == 'Y') {
    return ""
    } 
    val random = new Random
    val failureReason = List("Invalid Card Number", "Payment Declined", "Transaction Error", "Insufficient Funds")
    return failureReason(random.nextInt(failureReason.length))
  }

  def generateRow(): String = { //Tuple16[Int, Int, String, Int, String, String, String, Int, Float, String, String, String, String, Int, Char, String] = {
        // In some ways this sucks, in other ways it will make it much clearer what each index is.
    val customerGenerator = new Customer()
    val customer = customerGenerator.getRamCustomer()
    val productGenerator = new Product()
    val product = productGenerator.getRandomProduct()

    val orderID = id.toString()
    val customerID = customer._1.toString
    val customerName = customer._2
    val productID = product._4.toString
    val productName = product._2
    val productCategory = product._1
    val paymentType = customer._3
    val qty = getRandomQty()
    val price = product._3.toString   // In the return statemetn %.2f gives you tailing zeros
    val datetime = datetime_ts
    val country = customer._4
    val city = customer._5
    val website = getRandomWebsite()
    val transactionID = id.toString()
    val transactionSuccess = getRandomTxStatus()
    val failureReason = generateRandomReason(transactionSuccess)
    
    return f"$orderID,$customerID,$customerName,$productID,$productName,$productCategory,$paymentType,$qty,$price,$datetime,$country,$city,$website,$transactionID,$transactionSuccess,$failureReason" 
    
  }
}
