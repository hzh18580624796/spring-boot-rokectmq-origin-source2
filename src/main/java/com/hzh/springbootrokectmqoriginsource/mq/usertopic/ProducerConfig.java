package com.hzh.springbootrokectmqoriginsource.mq.usertopic;

import com.alibaba.fastjson.JSON;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.common.RemotingHelper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * @Author 18011618
 * @Date 10:31 2018/7/18
 * @Function 模拟用户消息发送
 */
@Configuration
public class ProducerConfig {
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
    public DefaultMQProducer produder() {
        DefaultMQProducer producer = new DefaultMQProducer(producerGroup);
        producer.setNamesrvAddr(namesrvAddr);
        try {
            producer.start();
        } catch (MQClientException e) {
            e.printStackTrace();
        }

        return producer;

    }
}
