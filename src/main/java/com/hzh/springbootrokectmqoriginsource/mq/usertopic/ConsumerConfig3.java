//package com.hzh.springbootrokectmqoriginsource.mq;//package com.hzh.springbootrokectmqoriginsource.mq;
//
//import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
//import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
//import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
//import org.apache.rocketmq.common.message.MessageExt;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//@Configuration
//public class ConsumerConfig3 {
//
//    /**
//     * 消费者的组名
//     */
//    @Value("${suning.rocketmq.conumerGroup}")
//    private String consumerGroup;
//
//    /**
//     * NameServer 地址
//     */
//    @Value("${suning.rocketmq.namesrvaddr}")
//    private String namesrvAddr;
//
//    @Bean
//    public DefaultMQPushConsumer consumer3() {
//
//        System.err.println("init defaultMQPushConsumer3");
//        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer(consumerGroup + "-3");
//        consumer.setNamesrvAddr(namesrvAddr);
//        try {
//            consumer.subscribe("user-topic", "user-tag");
//            consumer.registerMessageListener((MessageListenerConcurrently) (list, context) -> {
//                try {
//                    for (MessageExt messageExt : list) {
//                        System.err.println("消费消息-3: " + new String(messageExt.getBody()));//输出消息内容
//                    }
//                } catch (Exception e) {
//                    e.printStackTrace();
//                    return ConsumeConcurrentlyStatus.RECONSUME_LATER; //稍后再试
//                }
//                return ConsumeConcurrentlyStatus.RECONSUME_LATER; //消费成功
//            });
//            consumer.start();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//
//        return consumer;
//    }
//}