package producer

import scala.util.Random
import scala.collection.mutable.ListBuffer
import scala.util.control.Breaks._
//import scala.compiletime.ops.double

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
    ("Adrienne Penn"),
    ("Khloe Braun"),
    ("Joy Ward"),
    ("Dallas Evans"),
    ("Amaris Patton"),
    ("Crystal Austin"),
    ("Rowan Ingram"),
    ("Keenan Kelley"),
    ("Adyson Briggs"),
    ("Ali Arias"),
    ("Natasha Frederick"),
    ("Justine Jarvis"),
    ("Kolby Holder"),
    ("Felicity Giles"),
    ("Miles Frey"),
    ("Jayleen Webster"),
    ("Draven Estes"),
    ("Adrian Cox"),
    ("Ariel Brock"),
    ("Cierra Bernard"),
    ("Salvatore Richmond"),
    ("Alani Stevens"),
    ("Saniyah Rice"),
    ("Aracely Boyd"),
    ("Lindsay Franklin"),
    ("Hope Scott"),
    ("Jaidyn Barr"),
    ("Ulises Gutierrez"),
    ("Perla Zuniga"),
    ("Charlotte Vance"),
    ("Zain Donovan"),
    ("Eliana Higgins"),
    ("Amari Harding"),
    ("Marcos Morrow"),
    ("Humberto Ritter"),
    ("Hanna Valenzuela"),
    ("Peyton Johnston"),
    ("Alberto Warren"),
    ("Harmony Lewis"),
    ("Cullen Becker"),
    ("Alessandra Valdez"),
    ("Nico Blair"),
    ("Summer Maddox"),
    ("Layla Powers"),
    ("Hanna Hansen"),
    ("Raven Benitez"),
    ("Cindy Gibson"),
    ("Alyson Cox"),
    ("Lilah Mathews"),
    ("Gloria Best"),
    ("Kendall Moody"),
    ("Frederick Hoffman"),
    ("Tiara Wilkinson"),
    ("Haylee Davis"),
    ("Javier Mahoney"),
    ("Stacy Pugh"),
    ("Efrain Mcintyre"),
    ("Slade Bryan"),
    ("Leonel Hodges"),
    ("Rosa Cabrera"),
    ("Everett Mcknight"),
    ("Regina Mccormick"),
    ("Alyvia Moyer"),
    ("Rachel Cordova"),
    ("Malachi Sellers"),
    ("Sebastian Salazar"),
    ("Robert Fisher"),
    ("Celeste Hicks"),
    ("Malaki Blackburn"),
    ("Giovanni Mills"),
    ("Russell Massey"),
    ("Roy Sanford"),
    ("Blaine Kennedy"),
    ("Weston Fry"),
    ("Krista Pennington"),
    ("Rory Haas"),
    ("Addison David"),
    ("Jacob Krause"),
    ("Zoe Bentley"),
    ("Moriah Mejia"),
    ("Lamar Bernard"),
    ("Annabella Baker"),
    ("Monica Carpenter"),
    ("Aubrie Spencer"),
    ("Bradyn Goodman"),
    ("Ryker Sellers"),
    ("Logan Daugherty"),
    ("Dayana Fields"),
    ("Terrance Whitehead"),
    ("Tiffany Robertson"),
    ("Aryan Maldonado"),
    ("Josue Boone"),
    ("Jace Glass"),
    ("Belinda Perry"),
    ("Leyla Huffman"),
    ("Ariel Hatfield"),
    ("Tiffany Phillips"),
    ("Tobias Santos"),
    ("Jonah Charles"),
    ("Nikhil Conrad"),
    ("Randy Leonard"),
    ("Fletcher Hardy"),
    ("Trevon Orr"),
    ("Cheyanne Jacobs"),
    ("Connor Moreno"),
    ("Emery Hoover"),
    ("Kiara Buchanan"),
    ("Esteban Reyes"),
    ("Kamryn Perry"),
    ("Triston Hayden"),
    ("Kolton Mcdowell"),
    ("Lina Knapp"),
    ("Daisy Hobbs"),
    ("Giana Leblanc"),
    ("Gavin Hoover"),
    ("Cruz West"),
    ("Tate Decker"),
    ("Marisa Castro"),
    ("Skylar Singleton"),
    ("Quincy Ortega"),
    ("Jase Burke"),
    ("Macy Petty"),
    ("Rodney Fox"),
    ("Jaslene Schwartz"),
    ("Johanna Lynch"),
    ("Roman Mathews"),
    ("Damion Potter"),
    ("Timothy Fisher"),
    ("Angelique Galvan"),
    ("Tiara Perry"),
    ("Aylin Cline"),
    ("Clarissa Stein"),
    ("Milo Petty"),
    ("Joanna Mooney"),
    ("Hezekiah Rangel"),
    ("Finley Roberson"),
    ("Hudson Bowen"),
    ("Sloane Savage"),
    ("Scarlet Burke"),
    ("Damaris Luna"),
    ("Ali Young"),
    ("Irene Rivas"),
    ("Kimberly Steele"),
    ("Leonard Woodard"),
    ("Annabel Kerr"),
    ("Ricardo Hood"),
    ("Mariela Burton"),
    ("Ashanti Santiago"),
    ("Andreas Ortiz"),
    ("Colton Levine"),
    ("Gavin Holder"),
    ("Richard Casey"),
    ("Cassie Donaldson"),
    ("Elliana Young"),
    ("Remington Allen"),
    ("Alanna Parrish"),
    ("Ryland Villa"),
    ("Wayne Ramsey"),
    ("Rhett Hood"),
    ("Carl Le"),
    ("Gina Peterson"),
    ("Margaret Carr"),
    ("Alexus Bowman"),
    ("Landen Craig"),
    ("Amani Padilla"),
    ("Cullen Greer"),
    ("Caylee Levine"),
    ("Chance Bullock"),
    ("Dustin Ramirez"),
    ("Atticus Mcclure"),
    ("Yurem Hays"),
    ("Kaley Kelly"),
    ("Sidney Burnett"),
    ("Leonard Hobbs"),
    ("Duncan Hanson"),
    ("Jesse Berry"),
    ("Francis Travis"),
    ("Yahir Chase"),
    ("Addison Raymond"),
    ("Lauren Ramirez"),
    ("Angelique Hanna"),
    ("Lauryn Wilcox"),
    ("Nikolas Hodges"),
    ("Cara Parker"),
    ("Tiara Krause"),
    ("Jase Bridges"),
    ("Ismael Robbins"),
    ("Micah Chung"),
    ("Anabelle Roth"),
    ("Edith Moody"),
    ("Bridger Duncan"),
    ("Jordon Henson"),
    ("Ronald Campbell"),
    ("Chanel Acosta"),
    ("Rex Landry"),
    ("Emily Wiggins"),
    ("Chace Maddox"),
    ("Olivia Richard"),
    ("Ella Kelly"),
    ("Aydan Mccarthy"),
    ("Hillary Mcfarland"),
    ("Melvin Zimmerman"),
    ("Jabari Blake"),
    ("Miracle Stafford"),
    ("Avah Cooke"),
    ("Elias Collier"),
    ("Dexter Owens"),
    ("Danica Gillespie"),
    ("Kolton Kaufman"),
    ("Peyton Rangel"),
    ("Violet Madden"),
    ("Mark Stark"),
    ("Angelo Cortez")
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
  
  //Takes a Double type value for wieghting purposes
  def generateCustomerInfo(indicator: Double): Tuple4[String, String, String, Int] = {
    var customer = Tuple4("Name", "City", "Country", 0)
    var customerID = 0
    def normalCustomer(chance: Double): Tuple4[String, String, String, Int] = { 
      if(customerList.length < 5000 && chance > 0.25){                                             //5000 set as max number of created Customers
        val getName = names(Random.nextInt(names.length))
        val getLocation = locations(Random.nextInt(locations.length))
        if(customerList.length < 1){                                              //Only used for first customer to add to customer List
          customerID = nextCustomerID
          customer = Tuple4(getName, getLocation._1, getLocation._2, customerID) 
          customerList += Tuple4(getName, getLocation._1, getLocation._2, customerID)
          nextCustomerID += 1
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
    def regionalCustomer(regionIndicator: Int): Tuple4[String, String, String, Int] = {       //Need to Guard against empty values(selecting region with customers yet)
      var tempCustList = new ListBuffer[Tuple4[String, String, String, Int]]
      if(regionIndicator >= 8 && regionIndicator <= 25){                          //Get customer that has country as "United States""
        tempCustList = customerList.filter(_._3 == "United States")
        if(tempCustList.isEmpty){
          var tempLocaList = List[Tuple2[String, String]]()
          tempLocaList = locations.filter(_._2 == "United States")
          val getName = names(Random.nextInt(names.length))
          val getLocation = tempLocaList(Random.nextInt(tempLocaList.length))
          customerID = nextCustomerID
          customer = Tuple4(getName, getLocation._1, getLocation._2, customerID)
          nextCustomerID += 1
          newCustomer = true
        }
        else{  
          newCustomer = false
          customer = tempCustList(Random.nextInt(tempCustList.length))
        }
      }
      else if(regionIndicator < 8){                     //Get customer that has country as "Canada"
        tempCustList = customerList.filter(_._3 == "Canada")
        if(tempCustList.isEmpty){
          var tempLocaList = List[Tuple2[String, String]]()
          tempLocaList = locations.filter(_._2 == "Canada")
          val getName = names(Random.nextInt(names.length))
          val getLocation = tempLocaList(Random.nextInt(tempLocaList.length))
          customerID = nextCustomerID
          customer = Tuple4(getName, getLocation._1, getLocation._2, customerID)
          nextCustomerID += 1
          newCustomer = true
        }  
        else{  
          newCustomer = false
          customer = tempCustList(Random.nextInt(tempCustList.length))
        }
      }
      else if(regionIndicator > 25){                     //Get customer that has country as "Venezuela"
        tempCustList = customerList.filter(_._3 == "Venezuela")
        if(tempCustList.isEmpty){
          var tempLocaList = List[Tuple2[String, String]]()
          tempLocaList = locations.filter(_._2 == "Venezuela")
          val getName = names(Random.nextInt(names.length))
          val getLocation = tempLocaList(Random.nextInt(tempLocaList.length))
          customerID = nextCustomerID
          customer = Tuple4(getName, getLocation._1, getLocation._2, customerID)
          nextCustomerID += 1
          newCustomer = true
        }  
        else{  
          newCustomer = false
          customer = tempCustList(Random.nextInt(tempCustList.length))
        }
      }
      if(newCustomer){
        customerList += customer
      }
      return customer
    }
    
    if(indicator < 0.6){               //Select which method of customer generation is wanted    //Need to weight the options
      var reg = Random.nextInt(30)       //nextInt(a) where a is number of countries*10
      customer = regionalCustomer(reg)
    }
    else{
      var chance = Random.nextDouble()
      customer = normalCustomer(chance)
    }
    
    return customer
  }
  

  // def main(args: Array[String]): Unit = {
  // }
}


// object main extends Customer{
//     for(i <- 0 to 15){
//         var rangen = Random.nextDouble()
//         println(generateCustomerInfo(rangen))
//     }
//   }