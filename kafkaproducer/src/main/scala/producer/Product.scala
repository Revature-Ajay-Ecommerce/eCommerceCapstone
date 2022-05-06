package producer

import scala.collection.mutable.ListBuffer
import scala.util.Random
/*
The Product.scala file is where the products list is managed and includes all product info.
It contains one function in the Product class which returns one random product when called.
*/
class Product {
    val productList = Vector[Tuple4[String,String,Float,Int]](
        Tuple4("Electronics", "iPhone 13 Pro Max", 1099.00f, 1), 
        Tuple4("Electronics", "S95B OLED 4K Smart TV (2022)", 2199.99f, 2), 
        Tuple4("Electronics", "LG Eectronics 3-Door French Door Refridgerator", 2099.00f, 3),  
        Tuple4("Electronics", "MacBook Pro 14\" Laptop", 2499.00f, 4),
        Tuple4("Electronics", "Sony - PlayStation 5 Console", 499.00f, 5), 
        Tuple4("Electronics", "ONXE LED USB Clock Fan", 15.99f, 6), 
        Tuple4("Electronics", "Koss KPH7 Lightweight Portable Headphone", 5.99f, 7), 
        Tuple4("Electronics", "DDgro Electronics Travel Organizer", 10.98f, 8), 
        Tuple4("Electronics", "Tile Sage Pro 2-Pack", 62.99f, 9), 
        Tuple4("Electronics", "Bose 5.1 Channel Home Theater System", 3999.98f, 10), 
        Tuple4("Home Decor", "Organic Cotton 3-Piece Full/Queen Comforter Set", 170.00f, 11), 
        Tuple4("Home Decor", "Adjustable Memory Foam Standard/Queen Bed Pillow", 15.00f, 12), 
        Tuple4("Home Decor", "Supreme Softness Plush Full/Queen Blanket", 55.00f, 13), 
        Tuple4("Home Decor", "Therapedic Waterproof Cotton Queen Mattress Pad", 99.99f, 14), 
        Tuple4("Home Decor", "Perfect 24-Inch Queen Air Mattress", 329.99f, 15),
        Tuple4("Home Decor", "Geometric Doormat Blue", 13.00f, 16), 
        Tuple4("Home Decor", "Small Boston Fern", 10.00f, 17), 
        Tuple4("Home Decor", "Floral Photography Framed Print", 50.00f, 18), 
        Tuple4("Home Decor", "Maldone Curved Fully Upholstered Accent Chair Cream", 360.00f, 19), 
        Tuple4("Home Decor", "Embroidered Star Square Throw Pillow Blue", 16.00f, 20),
        Tuple4("Pet Supplies", "Royal Canin Indoor Adult Cat Food", 56.99f, 21), 
        Tuple4("Pet Supplies", "Whisker City 60-in Cat Tree", 164.99f, 22), 
        Tuple4("Pet Supplies", "Chance & Friends \"Zen\" Plush Turtle Toy", 5.00f, 23), 
        Tuple4("Pet Supplies", "Purina Adult Dry Dog Food - High Protein", 42.99f, 24), 
        Tuple4("Pet Supplies", "Spiky Football Dog Toy - Squeaker", 2.99f, 25),
        Tuple4("Pet Supplies", "TetraMin Tropical Fish Food Flakes", 5.28f, 26), 
        Tuple4("Pet Supplies", "Marineland Half Moon Tank", 279.98f, 27), 
        Tuple4("Pet Supplies", "Aqueon Power Filter 20 LED PRO", 34.98f, 28), 
        Tuple4("Pet Supplies", "Kaytee Forti Diet Pro Health Conure/Lovebird", 16.48f, 29), 
        Tuple4("Pet Supplies", "Prevue Pet Small Round Bird Cage", 54.98f, 30),
        Tuple4("Office", "Paper Mate EverStrong Wooden Pencils", 4.99f, 31),
        Tuple4("Office", "Pilot G2 Retractable Gel Pens", 20.89f, 32),
        Tuple4("Office", "Scotch Magic Tape", 4.99f, 33),
        Tuple4("Office", "TRU RED 1-Subject Notebook", 9.99f, 34),
        Tuple4("Office", "Staples File Folder 3 Tabs", 12.49f, 35),
        Tuple4("Office", "Elmer\'s School Glue", 2.29f, 36),
        Tuple4("Office", "Fiskars Softgrip Scissors", 2.79f, 37),
        Tuple4("Office", "Staples Hardboard Clipboard", 2.49f, 38),
        Tuple4("Office", "Texas Instruments TI-84 Plus CE", 156.99f, 39),
        Tuple4("Office", "HP DesignJet T830 Wide Format All-in-One Printer", 7270.99f, 40)
    )

    def generateProductInfo: Tuple4[String, String, Float, Int] = {
        val r = scala.util.Random
        var selectProduct = ("String", "String", 5.00f, 0)
        var cheapProdList = productList.filter(_._3 < 20.00)
        var semiProdList = productList.filter(x => (x._3 >= 20.00 && x._3 <= 100.00))
        var expensiveProdList = productList.filter(x => (x._3 > 100.00 && x._3 <= 500.00))
        var extravagentProdList = productList.filter(_._3 > 500.00)
        var budget = Random.nextInt(100)
        if(budget < 40){
            selectProduct = cheapProdList(Random.nextInt(cheapProdList.length))
        }
        else if(budget >= 40 && budget < 65){
            selectProduct = semiProdList(Random.nextInt(semiProdList.length))
        }
        else if(budget >= 65 && budget <= 90){
            selectProduct = expensiveProdList(Random.nextInt(expensiveProdList.length))            
        }
        else if(budget > 90){
            selectProduct = extravagentProdList(Random.nextInt(extravagentProdList.length))
        }
        return selectProduct
    }
}
