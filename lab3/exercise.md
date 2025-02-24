## Lab 3

0- In this folder run:

```
docker-compose up -d
```


1- Go to the subfolder exercise and run:

```
mkdir -p src/main/avro
mkdir -p src/main/java
mkdir -p src/test/java
mkdir -p src/main/resources
mkdir -p src/test/resources
```

Note: In Windows do it without `-p`

Now run `mvn clean compile`

2- Go to kafka folder and run kafka-topics
```
./bin/kafka-topics --create --zookeeper localhost:2181 \
--replication-factor 2 --partitions 3 --topic my_orders
```

3- Add the following two lines to the /etc/hosts files:

127.0.0.1 broker-1
127.0.0.1 broker-2

In Mac this should be in the file `/etc/hosts` and in Windows the file `c:\Windows\System32\Drivers\etc\hosts`

4- Create a producer called Main inside of a package com.honolulu

5-  Create a public static void main(String[] args) application, and create a array of states in the United States, using the following String

```
String stateString =
       "AK,AL,AZ,AR,CA,CO,CT,DE,FL,GA," +
       "HI,ID,IL,IN,IA,KS,KY,LA,ME,MD," +
       "MA,MI,MN,MS,MO,MT,NE,NV,NH,NJ," +
       "NM,NY,NC,ND,OH,OK,OR,PA,RI,SC," +
       "SD,TN,TX,UT,VT,VA,WA,WV,WI,WY";
```

6- In the main method, create a loop of your choosing and send a message with a random state as the key, and a random value from 10 to 100000 for the cost of the total order using java.util.Random.nextInt. Using a Future<RecordMetadata> or Callback and print the metadata either using System.out.println or System.out.format, you can use a closure to enclose the key and value and print the content.

7- Have a Thread.sleep with a random amount of time from 5 to 30 seconds using the same java.util.Random instance to represent some time between messages to mimic the real world

8- Run in your IDE, or run in Maven using mvn exec:java -Dexec.mainClass="com.honolulu.Main" -Dexec.cleanupDaemonThreads=false

9- Verify with the console consumer you got all messages.

```
./bin/kafka-console-consumer \
  --bootstrap-server broker-2:9092 \
  --topic my_orders --from-beginning \
  --key-deserializer org.apache.kafka.common.serialization.StringDeserializer \
  --value-deserializer org.apache.kafka.common.serialization.DoubleDeserializer \
  --property print.key=true \
  --property key.separator=,
```

10- Shut down everything:

```
docker-compose down
```

