package datagen
import datagen.Order
import java.text.SimpleDateFormat
import java.util.Calendar
import scala.collection.mutable.ListBuffer

class AlchemyDataGenerator {

  var currentOrderID = 1
  
  def generateData(): String = {

  val format = new SimpleDateFormat("MM-dd-y HH:mm:ss")
  val currentDay = format.format(Calendar.getInstance().getTime())
  val order = new Order(currentOrderID, currentDay)
  var row = order.generateRow()

  currentOrderID+=2

  return row
  
  }
}
