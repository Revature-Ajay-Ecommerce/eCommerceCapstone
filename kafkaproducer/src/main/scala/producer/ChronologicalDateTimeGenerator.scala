// package producer

// import java.time._
// import java.time.format.DateTimeFormatter
// import scala.collection.mutable.Stack
// import scala.util.Random

// class TimestampTally(max: Int, current: Int) {
//     val maxTally = max
//     var currentTally = current
    
//     def atMax(): Boolean = {
//         return currentTally == maxTally
//     }

//     def add(): Unit = {
//         if(!atMax()) {
//             currentTally += 1
//         }   
//     }
// }

// class DateTimeGenerator() {
//     // Trends to create:
//     // [ ] More sales as time goes on, limit timestamp generation for earlier dates
//     // [x] More sales in the evenings
//     // [ ] More sales during the holiday season
//     // Start year will be 2013
//     // End year will be 2022
//     val tally2013 = new TimestampTally(896, 0)
//     val tally2014 = new TimestampTally(941, 0)
//     val tally2015 = new TimestampTally(988, 0)
//     val tally2016 = new TimestampTally(1037, 0)
//     val tally2017 = new TimestampTally(1089, 0)
//     val tally2018 = new TimestampTally(1198, 0)
//     val tally2019 = new TimestampTally(1318, 0)
//     val tally2020 = new TimestampTally(1450, 0)
//     val tally2021 = new TimestampTally(1666, 0)
//     val tally2022 = new TimestampTally(1917, 0)

//     var tallyStack = Stack[TimestampTally]()
//     tallyStack.push(tally2022)
//     tallyStack.push(tally2021)
//     tallyStack.push(tally2020)
//     tallyStack.push(tally2019)
//     tallyStack.push(tally2018)
//     tallyStack.push(tally2017)
//     tallyStack.push(tally2016)
//     tallyStack.push(tally2015)
//     tallyStack.push(tally2014)
//     tallyStack.push(tally2013)

//     var currentTally = tallyStack.pop

//     val rand = new Random()

//     val holidaySeasonMonths = List(Month.of(10), Month.of(11), Month.of(12))
//     var holidaySeasonExtra = true

//     var currentYear = Year.of(2013)
//     var currentMonthDay = MonthDay.of(1,1)
//     var currentTime = generateTime()

//     def generateDateTime: String = {
//         val currentDateTime = LocalDateTime.of(currentYear.getValue(), currentMonthDay.getMonthValue(), currentMonthDay.getDayOfMonth(), currentTime.getHour(), currentTime.getMinute())
//         val output = currentDateTime.format(DateTimeFormatter.ofPattern("y-MM-dd HH:mm"))

//         if(!currentTally.atMax()) {
//             // doubles the amount of timestamps if it is the holiday season
//             if(holidaySeasonMonths contains currentMonthDay.getMonth()) {
//                 if(holidaySeasonExtra) {
//                     holidaySeasonExtra = false
//                 }
//                 else {
//                     tally2013.add()
//                     if (currentTally.currentTally % (currentTally.maxTally / 365) == 0) {
                        
//                     }
//                     holidaySeasonExtra = true
//                 }
//             }
            


//         }

//         else {
//             currentTally = tallyStack.pop
//             // reset everything
//             currentYear = Year.of(currentYear.getValue() + 1)
//             currentMonthDay = MonthDay.of(1,1)
//             currentTime = generateTime()
//         }

//         return output
//     }

//     def generateTime(): LocalTime = {
//         val seed = rand.nextFloat()

//         // 80% of the time the order will be in the evening
//         if (seed < 0.8f) {
//             return LocalTime.of(17 + rand.nextInt(7), rand.nextInt(60))
//         }
//         // else its some other time
//         return LocalTime.of(rand.nextInt(17), rand.nextInt(60))
//     }

// }