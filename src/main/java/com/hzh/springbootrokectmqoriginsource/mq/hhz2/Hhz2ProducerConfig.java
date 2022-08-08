package com.hzh.springbootrokectmqoriginsource.mq.hhz2;

import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author 18011618
 * @Date 10:31 2018/7/18
 * @Function 模拟用户消息发送
 */
@Configuration
public class Hhz2ProducerConfig {
    /**
     * 生产者的组名
     */
    @Value("${suning.rocketmq.producerGroup}")
    private String producerGroup;

    /**
     * NameServer 地址
     */
    @Value("${suning.rocketmq.namesrvaddr}")
    private String namesrvAddr;


    @Bean
    public DefaultMQProducer hhz2Produder() {
        DefaultMQProducer producer = new DefaultMQProducer("hhz2");
        producer.setNamesrvAddr(namesrvAddr);
        try {
            producer.start();
        } catch (MQClientException e) {
            e.printStackTrace();
        }

        return producer;

    }
}
