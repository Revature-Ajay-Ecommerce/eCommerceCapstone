package ecommerce
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
        Tuple4("Bedding", "Organic Cotton 3-Piece Full/Queen Comforter Set", 170.00f, 6), 
        Tuple4("Bedding", "Adjustable Memory Foam Standard/Queen Bed Pillow", 15.00f, 7), 
        Tuple4("Bedding", "Supreme Softness Plush Full/Queen Blanket", 55.00f, 8), 
        Tuple4("Bedding", "Therapedic Waterproof Cotton Queen Mattress Pad", 99.99f, 9), 
        Tuple4("Bedding", "Perfect 24-Inch Queen Air Mattress", 329.99f, 10), 
        Tuple4("Pet Supplies", "Royal Canin Indoor Adult Cat Food", 56.99f, 11), 
        Tuple4("Pet Supplies", "Whisker City 60-in Cat Tree", 164.99f, 12), 
        Tuple4("Pet Supplies", "Chance & Friends \"Zen\" Plush Turtle Toy", 5.00f, 13), 
        Tuple4("Pet Supplies", "Purina Adult Dry Dog Food - High Protein", 42.99f, 14), 
        Tuple4("Pet Supplies", "Spiky Football Dog Toy - Squeaker", 2.99f, 15)
        )

    def generateProductInfo: Tuple4[String, String, Float, Int] = {
        val r = scala.util.Random
        val current_product = r.nextInt(productList.length) //returns a number from 0-n where n is the no. of products in productList.
        
        productList(current_product)
    }
}
