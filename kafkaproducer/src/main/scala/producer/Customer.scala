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