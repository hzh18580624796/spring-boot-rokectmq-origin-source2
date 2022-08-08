//package com.hzh.springbootrokectmqoriginsource;
//
//import lombok.extern.slf4j.Slf4j;
//import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
//import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
//import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
//import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
//import org.apache.rocketmq.client.exception.MQClientException;
//import org.apache.rocketmq.client.producer.DefaultMQProducer;
//import org.apache.rocketmq.common.consumer.ConsumeFromWhere;
//import org.apache.rocketmq.common.message.MessageExt;
//import org.apache.rocketmq.common.protocol.heartbeat.MessageModel;
//import org.apache.rocketmq.remoting.common.RemotingHelper;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.stereotype.Component;
//
//import javax.annotation.PostConstruct;
//import javax.annotation.PreDestroy;
//import java.util.List;
//
//@Component
//@Slf4j
//public class MQPushConsumer implements MessageListenerConcurrently {
//
//    DefaultMQProducer defaultMQProducer = null;
//    private final DefaultMQPushConsumer consumer = new DefaultMQPushConsumer("group1");
//
//    @Value("${rocketmq.consumer.namesrvAddr}")
//    private String rocketmqNameServer;
//    @Value("${rocketmq.consumer.topic}")
//    private String topic;
//
//    /**
//     * 初始化
//     */
//    @PostConstruct //java5中引入，指在项目启动的时候执行这个方法
//    public void start() {
//        try {
//            consumer.setNamesrvAddr(rocketmqNameServer);
//            consumer.setConsumerGroup("group1");
//            consumer.setInstanceName("group1");
//            consumer.setVipChannelEnabled(false);
//            //从消费队列头开始消费
//            consumer.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_FIRST_OFFSET);
//            consumer.setMessageModel(MessageModel.CLUSTERING);
//            //订阅主题
//            consumer.subscribe("TRAFFIC_EVENT", "*");
//            consumer.registerMessageListener(this);
//            consumer.start();
//            log.info("[启动日志]：MQ消费者已启动");
//        } catch (MQClientException e) {
//            e.printStackTrace();
//        }
//    }
//
//    @Override
//    public ConsumeConcurrentlyStatus
//    consumeMessage(List<MessageExt> msgs, ConsumeConcurrentlyContext consumeConcurrentlyContext) {
//        try {
//            for (MessageExt msg : msgs) {
//                String messageBody = new String(msg.getBody(), RemotingHelper.DEFAULT_CHARSET);
//                log.info("MQ:消费者接受新消息：{}{}{}{}{}", msg.getMsgId(), msg.getTopic(), msg.getTags(), msg.getKeys(), messageBody);
//            }
//        } catch (Exception e) {
//            log.error(e.getMessage(), e);
//        }
//        return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
//    }
//
//    @PreDestroy //在关闭spring容器后释放一些资源*
//    public void stop() {
//        if (null != consumer) {
//            consumer.shutdown();
//            log.error("MQ: 关闭消费者");
//        }
//    }
//}
