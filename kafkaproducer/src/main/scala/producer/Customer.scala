package producer

import scala.util.Random
import scala.collection.mutable.ListBuffer
import scala.util.control.Breaks._

class Customer {
  val names = List(
    ("John Appleseed"),
    ("Inayah Kaiser"),
    ("Gabriel Lawrence"),
    ("Dora Atkins"),
    ("Fox Combs"),
    ("Quinn Worthington"),
    ("Suhail Southern"),
    ("Maxime Britt"),
    ("Sherri Wise"),
    ("Monique Bradford"),
    ("Brittney Henderson"),
    ("Daisy-Mae Mosley"),
    ("Robson Flores"),
    ("Theo Archer"),
    ("Adrienne Penn")
  )

  val locations = List(
    ("Miami", "United States"),
    ("Boston", "United States"),
    ("New York", "United States"),
    ("New Orleans", "United States"),
    ("Seattle", "United States"),
    ("San Diego", "United States"),
    ("Los Angeles", "United States"),
    ("Chicago", "United States"),
    ("Toronto", "Canada"),
    ("Vancouver", "Canada"),
    ("Montreal", "Canada"),
    ("Ottawa", "Canada"),
    ("Windsor", "Canada"),
    ("Caracas", "Venezuela"),
    ("La Asuncion", "Venezuela")    // Sadly I had to remove the ó due to formatting problems :(
  )

  var nextCustomerID: Int = 1
  var customerList = new ListBuffer[Tuple4[String, String, String, Int]]      //Initiate List of created customers
  var newCustomer = false
  
  def generateCustomerInfo(): Tuple4[String, String, String, Int] = {
    var customer = Tuple4("Name", "City", "Country", 0)
    var customerID = 0
    if(customerList.length < 5000){                                             //5000 set as max number of created Customers
      val getName = names(Random.nextInt(names.length))
      val getLocation = locations(Random.nextInt(locations.length))
      if(customerList.length < 1){                                              //Only used for first customer to add to customer List
        customerID = nextCustomerID
        customer = Tuple4(getName, getLocation._1, getLocation._2, customerID) 
        customerList += Tuple4(getName, getLocation._1, getLocation._2, customerID)
        nextCustomerID += 1
        println("First append")
      }
      else{
        breakable{
          for(cust <- customerList){    //Won't work for empty List                 //Iterate through customer List to check if existing
            if(cust._1 == getName && cust._2 == getLocation._1 && cust._3 == getLocation._2){
              customerID = cust._4
              newCustomer = false
              break
            }
            else{
              newCustomer = true
            }
          }
        }
        if(newCustomer){
          customerID = nextCustomerID
          customerList += Tuple4(getName, getLocation._1, getLocation._2, customerID)
          nextCustomerID += 1                       //Increases by one only if new customer is made
        }
      }

      customer = Tuple4(getName, getLocation._1, getLocation._2, customerID)
      
    }
    else{                                                                       //Pick from customer List if 5000 customers exist
      customer = customerList(Random.nextInt(customerList.length))
    }
    return customer
  }

  // def main(args: Array[String]): Unit = {
  // }
}


// object main extends Customer{
//     for(i <- 0 to 10){
//       println(generateCustomerInfo())
//     }
//   }