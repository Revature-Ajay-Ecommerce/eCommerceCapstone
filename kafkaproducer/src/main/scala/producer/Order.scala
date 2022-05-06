package producer

import scala.collection.mutable.ListBuffer
//import scala.compiletime.ops.string

class Order {
    val paymentOptions = Vector("Visa", "Master Card", "Discover", "PayPal", "ApplePay")
    val websites = Vector("www.Amazon.com", "www.BestBuy.com", "www.Walmart.com", "www.Ebay.com", "www.Etsy.com")
    var order_id = 2    // starting at 2 to avoid colliding with other data generator
    val productGenerator = new Product()

    def incrementOrderID(): Unit = {              //Increment Order_ID to make sure unique values are used
        order_id = order_id + 2 // incrementing by 2 to avoid colliding with the other data generator
    }

    def generateOrderInfo(): Tuple8[String, String, Float, Int, Int, String, String, Int] = {           //Generation of one order at a time
        var r = scala.util.Random                  //Random Number of Products

        //var products_ordered = generateProductList(r.nextInt(5) + 1)      //Get List of Products + Qty

        // could add a trend here where a certain payment type or website is more popular
        val ranPayment = paymentOptions(r.nextInt(paymentOptions.length)).toString    //Select random Payment Type
        val ranWebsite = websites(r.nextInt(websites.length)).toString        //Select random Website       //Select random Website

        val product = productGenerator.generateProductInfo

        val orderQTY = scala.util.Random.nextInt(10) + 1

        val order = Tuple8(product._1, product._2, product._3.toFloat, product._4, orderQTY, ranPayment, ranWebsite, order_id)

        incrementOrderID()

        return order
    }
}
