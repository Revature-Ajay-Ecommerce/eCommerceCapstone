package ecommerce

import scala.util.Random

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

  def generateCustomerInfo(): Tuple4[String, String, String, Int] = {
    val getName = names(Random.nextInt(names.length))
    val getLocation = locations(Random.nextInt(locations.length))
    val customer = (getName, getLocation._1, getLocation._2, nextCustomerID)
    // println(customer)
    nextCustomerID += 1
    return customer
  }
}
