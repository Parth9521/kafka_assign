package com.kakashi.producer;

import com.kakashi.model.user_data;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.Properties;
import java.util.Random;

public class Producer {
    public static void main(String[] args){



        Properties properties = new Properties();
        properties.put("bootstrap.servers", "localhost:9092");
        properties.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        properties.put("value.serializer", "com.kakashi.serializer.U_Serializer");

        Random random=new Random();

        int age=24;

        KafkaProducer kafkaProducer = new KafkaProducer(properties);
        try{
            {
                user_data userdata = new user_data(1," Parth ",age,"B.Tech");
                System.out.println(userdata);
                kafkaProducer.send(new ProducerRecord("user", userdata));
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            kafkaProducer.close();
        }
    }
}