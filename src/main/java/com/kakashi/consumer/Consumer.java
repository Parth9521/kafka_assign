package com.kakashi.consumer;

import com.kakashi.model.user_data;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class Consumer {

    public static void main(String[] args) {
        ConsumerListener c = new ConsumerListener();
        Thread thread = new Thread(c);
        thread.start();
    }
    public static void consumer() {
        Properties properties = new Properties();
        properties.put("bootstrap.servers", "localhost:9092");
        properties.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        properties.put("value.deserializer", "com.kakashi.deserializer.U_Deserializer");
        properties.put("group.id", "test-group");

        KafkaConsumer<String, user_data> kafkaConsumer = new KafkaConsumer(properties);
        List topics = new ArrayList();
        topics.add("user");
        kafkaConsumer.subscribe(topics);
        try{

            while (true){
                ConsumerRecords<String, user_data> records = kafkaConsumer.poll(10000);
                for (ConsumerRecord<String, user_data> record: records){
                    System.out.println(record.value());

                    BufferedWriter buffer = new BufferedWriter(new FileWriter("result.txt", true));
                    buffer.write(record.value()+"\n"); // this code will write the user records in result.txt file.
                    buffer.close();
                }
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }finally {
            kafkaConsumer.close();
        }
    }
}

class ConsumerListener implements Runnable {


    @Override
    public void run() {
        Consumer.consumer();
    }
}