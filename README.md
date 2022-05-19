# eCommerce Capstone Project

## Table of contents
* [Project Description](#project-description)
* [Technologies Used](#technologies-used)
* [Features](#features)
* [Getting Started](#getting-started)
* [Usage](#usage)
* [Contributors](#contributors)
* [License](license)

## Project Description

In this project we were created 

## Technologies Used

AWS EC2 Instance
- JDK - version 1.8.0_321
- Hadoop - version 3.3.1
- Scala - version 2.11.12
- Spark - version 3.1.3
- Kafka - version 2.4.1

- Hortonworks HDP 2.6.5
- gitSCM - version 2.35.1
- GitHub

## Features

- Generatre Random eCommerce Data with trends
- Publish data to a Kafka Topic on EC2 Instance
- Consume Insurance data from Kafka Topic on the EC2 Instance
- Analyze data and create visualizatinos

## Getting Started
Clone this repository:

    git clone https://github.com/Revature-Ajay-Ecommerce/eCommerceCapstone.git
    
Open the program you want to build:

- To open and build Producer, open the `kafkaproducer` directory using the Open Folder Option in VS Code

- To open and build Consumer, open the `kafkaconsumer` directory using the Open Folder Option in VS Code

In the sbt shell:

Compile using:

    compile

Build the .jar file:

    package
    
This will create the dedicated .jar in the `target/scala-2.11/` folder.
    
Move the JARs to the VM:

    cd .\target\scala-2.11\
    scp -P 2222 <JAR file name here> maria_dev@127.0.0.1:~
    
Create the topic in Kafka):

    $KAFKA_HOME/bin/kafka-topics.sh --create --zookeeper localhost:2181 --replication-factor 1 --partitions 3 --topic ecommerce

## Usage

    spark-submit --packages org.apache.spark:spark-sql-kafka-0-10_2.11:2.3.4 kafkaproducer_2.11-0.1.0-ECOMMERCE.jar
    spark-submit --packages org.apache.spark:spark-sql-kafka-0-10_2.12:3.0.0 kafkaconsumer_2.11-0.1.0-SNAPSHOT.jar

## Contributors
 - Antonio Almonte-Cabrera
 - Mansour Al Malaay
 - Kaya Proteau
 - Matthew Scalzo
 - Jordan Bruno Swan
 - David Tennessee
 - Drake Tubbs
 - Nicholas Young
 - Javier Zapata

## License
This project uses the following license: [MIT License](https://github.com/Revature-Ajay-Ecommerce/eCommerceCapstone/blob/02873be5f4a4b1ef13772636dea55ecbb8edb060/LICENSE)

