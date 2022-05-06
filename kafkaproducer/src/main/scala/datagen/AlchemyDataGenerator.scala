package datagen

import java.text.SimpleDateFormat
import java.util.Calendar
import scala.collection.mutable.ListBuffer
import java.lang.Math


class AlchemyDataGenerator {

  // def main(args: Array[String]): Unit = {
  //   val runAll = new AlchemyOrders
 
  //   val orderList = new ListBuffer[String]()
  //   for(itr <- 1 to 12500){
  //       orderList += runAll.generateRow()
  //   }
  //   for(o <- orderList){
  //       println(o)
  //   }
  // }

    val orderGen = new AlchemyOrders

    def generateOrder(): String = {
        return orderGen.generateRow()
    }
}
