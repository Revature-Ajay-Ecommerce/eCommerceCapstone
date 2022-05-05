package producer

object Test {

    val vDataGen = new VanquishDataGenerator()


    def main(args: Array[String]) {
        // establish connection to topic
        // create properties

        println("This is the test of Vanquish Data Generator")
        println("We are going to generate 20 rows")
        for(i <- 1 to 20) {
            // publish to topic method here
            println(vDataGen.generateOrder())
        }
    }

    def publishToTopic(row: String) {
        // doing any pre processing or formatting to the row so that it can be used
        // then actual send() it to the topic
    }
}