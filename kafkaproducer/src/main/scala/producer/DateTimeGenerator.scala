package producer

import java.time._
import java.time.format.DateTimeFormatter
import scala.collection.mutable.Stack
import scala.util.Random


class DateTimeGenerator() {
    // Trends to create:
    // [ ] More sales as time goes on, limit timestamp generation for earlier dates
    // [x] More sales in the evenings
    // [ ] More sales during the holiday season
    // Start year will be 2013
    // End year will be 2022

    val rand = new Random()

    // There is a 3 to 1 chance Oct, Nov, or Dec will be selected over the other months
    val possibleMonths = List(Month.of(1), Month.of(2), Month.of(3), Month.of(4), Month.of(5), Month.of(6), Month.of(7), Month.of(8), Month.of(9), Month.of(10), Month.of(11), Month.of(12), Month.of(10), Month.of(11), Month.of(12), Month.of(10), Month.of(11), Month.of(12))

    def generateDateTime(): String = {
        var currentYear = generateYear()
        var currentMonthDay = generateMonthDay(currentYear.isLeap())
        var currentTime = generateTime()

        val currentDateTime = LocalDateTime.of(currentYear.getValue(), currentMonthDay.getMonthValue(), currentMonthDay.getDayOfMonth(), currentTime.getHour(), currentTime.getMinute())
        val output = currentDateTime.format(DateTimeFormatter.ofPattern("y-MM-dd HH:mm"))

        return output
    }

    def generateTime(): LocalTime = {
        val seed = rand.nextFloat()

        // 80% of the time the order will be in the evening
        if (seed < 0.8f) {
            return LocalTime.of(17 + rand.nextInt(7), rand.nextInt(60))
        }
        // else its some other time
        return LocalTime.of(rand.nextInt(17), rand.nextInt(60))
    }

    def generateMonthDay(leapYear: Boolean): MonthDay = {
        val randomMonth = possibleMonths(rand.nextInt(possibleMonths.length))
        val randomDay = 1 + rand.nextInt(randomMonth.length(leapYear))

        return MonthDay.of(randomMonth, randomDay)
    }

    def generateYear(): Year = {
        val yearSeed = rand.nextFloat()

        if(yearSeed <= 0.01) {
            return Year.of(2013)
        }
        if(yearSeed > 0.01 && yearSeed <= 0.02 ) {
            return Year.of(2014)
        }
        if(yearSeed > 0.02 && yearSeed <= 0.05 ) {
            return Year.of(2015)
        }
        if(yearSeed > 0.05 && yearSeed <= 0.1 ) {
            return Year.of(2016)
        }
        if(yearSeed > 0.1 && yearSeed <= 0.16 ) {
            return Year.of(2017)
        }
        if(yearSeed > 0.16 && yearSeed <= 0.24 ) {
            return Year.of(2018)
        }
        if(yearSeed > 0.24 && yearSeed <= 0.36 ) {
            return Year.of(2019)
        }
        if(yearSeed > 0.36 && yearSeed <= 0.5 ) {
            return Year.of(2020)
        }
        if(yearSeed > 0.5 && yearSeed <= 0.65 ) {
            return Year.of(2021)
        }

        // yearSeed > 0.65
        return Year.of(2022)
    }
}