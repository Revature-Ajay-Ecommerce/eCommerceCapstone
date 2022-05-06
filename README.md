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


## Technologies Used

	
## Features

## Getting Started
Clone this repository:

    git clone https://github.com/Revature-Ajay-Ecommerce/eCommerceCapstone.git
    
Build the JARs in VS Code

    package
    
Move the JARs to your VM:

    scp -P 2222 <name of jar> maria_dev@127.0.0.1:~
    
Create the topic in Kafka):

    $KAFKA_HOME/bin/kafka-topics.sh --create --zookeeper localhost:2181 --replication-factor 1 --partitions 3 --topic ecommerce

## Usage

    spark-submit --packages org.apache.spark:spark-sql-kafka-0-10_2.11:2.3.4 kafkaproducer_2.11-0.1.0-SNAPSHOT.jar

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

