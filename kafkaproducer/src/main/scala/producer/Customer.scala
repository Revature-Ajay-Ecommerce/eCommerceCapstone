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
        ("Quebec City", "Canada"),
        ("Calgary", "Canada"),
        ("Edmonton", "Canada"),
        ("Vancouver", "Canada"),
        ("Montreal", "Canada"),
        ("Ottawa", "Canada"),
        ("Windsor", "Canada"),
        ("Caracas", "Venezuela"),
        ("La Asuncion", "Venezuela"),    // Sadly I had to remove the ó due to formatting problems :(
        ("Maracaibo", "Venezuela"),
        ("Barquisimeto", "Venezuela"),
        ("Guayana City", "Venezuela"),
        ("Maracay", "Venezuela"),
        ("San Cristobal", "Venezuela"),
        ("Valencia", "Venezuela"),
        ("Tokyo","Japan"),
        ("Kyoto","Japan"),
        ("Osaka","Japan"),
        ("Fukuoka","Japan"),
        ("Sapporo","Japan"),
        ("Nagoya","Japan"),
        ("Yokohama","Japan"),
        ("Sendai","Japan"),
        ("Mexico City","Mexico"),
        ("Guadalajara","Mexico"),
        ("Merida","Mexico"),
        ("Puebla","Mexico"),
        ("Oaxaca","Mexico"),
        ("Tijuana","Mexico"),
        ("Puerto Vallarta","Mexico"),
        ("Cancun","Mexico")
        )

    var getName = "Name"
    var getLocation = ("City", "Region")
    var nextCustomerID: Int = 1
    var customerID = 0
    var customer = Tuple4("Name", "City", "Country", 0)
    var customerList = new ListBuffer[Tuple4[String, String, String, Int]]      //Initiate List of created customers
    var newCustomer = false
  
    //Takes a Double type value for weighting purposes
    def generateCustomerInfo(indicator: Double): Tuple4[String, String, String, Int] = {
        if(indicator >= 0.7){
            if(customerList.length < 5000){
                customer = createNewCustomer()
            }
            else{
                customer = customerList(Random.nextInt(customerList.length))
            }
        }
        else if(indicator < 0.7){
            var region = 0
            var ranRegion = Random.nextInt(100)             //Weighting based on %GDP of total GDP from all included countries from 2017
            if(ranRegion < 70){
                region = 0        //United States
            }
            else if(ranRegion >= 70 && ranRegion < 76){
                region = 1        //Canada
            }
            else if(ranRegion >= 76 && ranRegion < 77){
                region = 2        //Venezuela
            }
            else if(ranRegion >= 77 && ranRegion < 95){
                region = 3        //Japan
            }
            else if(ranRegion >= 95){
                region = 4        //Mexico
            }
            customer = regionCustomer(region)
        }
        return customer
    }
    
    def createNewCustomer(): Tuple4[String, String, String, Int] = {
        getName = names(Random.nextInt(names.length))
        getLocation = locations(Random.nextInt(locations.length))
        customerID = nextCustomerID
        customer = Tuple4(getName, getLocation._1, getLocation._2, customerID) 
        customerList += customer
        nextCustomerID += 1
        return customer
        }
    
    var regionList = new ListBuffer[String]()
    for(x <- locations){
        regionList += x._2
    }
    var uniqueRegionList = regionList.distinct

    def regionCustomer(regionIndicator: Int): Tuple4[String, String, String, Int] = {      //Need to Guard against empty values(selecting region with customers yet)
        var tempCustList = new ListBuffer[Tuple4[String, String, String, Int]]
        var region = uniqueRegionList(regionIndicator)
        tempCustList = customerList.filter(_._3 == region)
        if(tempCustList.isEmpty){
            var tempLocaList = List[Tuple2[String, String]]()
            tempLocaList = locations.filter(_._2 == region)
            val getName = names(Random.nextInt(names.length))
            val getLocation = tempLocaList(Random.nextInt(tempLocaList.length))
            customerID = nextCustomerID
            customer = Tuple4(getName, getLocation._1, getLocation._2, customerID)
            newCustomer = true
        }
        else{  
            newCustomer = false
            customer = tempCustList(Random.nextInt(tempCustList.length))
        }
        if(newCustomer){
            nextCustomerID += 1
            customerList += customer
        }
        return customer
    }
}