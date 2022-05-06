package datagen

import datagen.AlchemyOrders
import java.text.SimpleDateFormat
import java.util.Calendar
import scala.collection.mutable.ListBuffer
import java.lang.Math


object AlchemyDataGenerator {

  def main(args: Array[String]): Unit = {
    val runAll = new AlchemyOrders
 
    val orderList = new ListBuffer[String]()
    for(itr <- 1 to 12500){
        orderList += runAll.generateRow()
    }
    for(o <- orderList){
        println(o)
    }
  }
}
