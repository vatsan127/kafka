package com.learn.kafka.constants;


import java.util.Arrays;
import java.util.List;

public class Constant {
    /*kafka Topics*/
    public static final String kafkaTopic_myTopic = "myTopic";
    public static final String kafkaTopic_phones = "phones";

    /*kafka keys*/
    public static final String kafkaKeys_apple = "apple";
    public static final String kafkaKeys_samsung = "samsung";
    public static final String kafkaKeys_oppo = "oppo";
    public static final String kafkaKeys_redmi = "redmi";
    public static final List<String> kafkaKeys_List = Arrays.asList(kafkaKeys_apple, kafkaKeys_samsung, kafkaKeys_redmi, kafkaKeys_oppo);

    /*Required Constants*/
    public static final int twoSecInMillis = 2000;
    public static final boolean runnable = true;


}