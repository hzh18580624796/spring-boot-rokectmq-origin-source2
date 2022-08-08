package com.hzh.springbootrokectmqoriginsource.web;

import com.alibaba.fastjson.JSON;
import com.hzh.springbootrokectmqoriginsource.mq.hhz2.Hhz2Content;
import com.hzh.springbootrokectmqoriginsource.mq.hhz6.Hhz6Content;
import com.hzh.springbootrokectmqoriginsource.mq.usertopic.UserContent;
import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.common.RemotingHelper;
import org.apache.rocketmq.remoting.exception.RemotingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.UnsupportedEncodingException;
import java.util.concurrent.atomic.AtomicInteger;

@RestController
public class MqController {

    @Autowired
    @Qualifier("produder")
    private DefaultMQProducer producer;
//    @Autowired
//    @Qualifier("hhzProduder")
//    private DefaultMQProducer hhzProduder;
    @Autowired
    @Qualifier("hhz2Produder")
    private DefaultMQProducer hhz2Produder;
    @Autowired
    @Qualifier("hhz6Produder")
    private DefaultMQProducer hhz6Produder;

    private AtomicInteger counter = new AtomicInteger(0);

    @GetMapping("/tt")
    public void tt() throws InterruptedException, RemotingException, MQClientException, MQBrokerException, UnsupportedEncodingException {

        UserContent userContent = new UserContent(String.valueOf(counter.incrementAndGet()), "abc" + counter.get());
        String jsonstr = JSON.toJSONString(userContent);

        System.out.println("发送消息:" + jsonstr);

        Message message = new Message("user-topic", "user-tag", jsonstr.getBytes(RemotingHelper.DEFAULT_CHARSET));

        SendResult result = producer.send(message);
        System.err.println("发送响应：MsgId:" + result.getMsgId() + "，发送状态:" + result.getSendStatus());
    }

//    @GetMapping("/hhz")
//    public void hhz() throws InterruptedException, RemotingException, MQClientException, MQBrokerException, UnsupportedEncodingException {
//
//        HhzContent userContent = new HhzContent(String.valueOf(counter.incrementAndGet()), "hhz" + counter.get());
//        String jsonstr = JSON.toJSONString(userContent);
//
//        System.out.println("发送消息:" + jsonstr);
//
//        Message message = new Message("hhz", "hhz-tag", jsonstr.getBytes(RemotingHelper.DEFAULT_CHARSET));
//
//        SendResult result = hhzProduder.send(message);
//        System.err.println("发送响应：MsgId:" + result.getMsgId() + "，发送状态:" + result.getSendStatus());
//    }

    @GetMapping("/hhz2")
    public void hhz2() throws InterruptedException, RemotingException, MQClientException, MQBrokerException, UnsupportedEncodingException {

        Hhz2Content userContent = new Hhz2Content(String.valueOf(counter.incrementAndGet()), "hhz2" + counter.get());
        String jsonstr = JSON.toJSONString(userContent);

        System.out.println("发送消息:" + jsonstr);

        Message message = new Message("hhz2", "hhz2-tag", jsonstr.getBytes(RemotingHelper.DEFAULT_CHARSET));

        SendResult result = hhz2Produder.send(message);
        System.err.println("发送响应：MsgId:" + result.getMsgId() + "，发送状态:" + result.getSendStatus());
    }

    @GetMapping("/hhz6")
    public void hhz6() throws InterruptedException, RemotingException, MQClientException, MQBrokerException, UnsupportedEncodingException {

        Hhz6Content userContent = new Hhz6Content(String.valueOf(counter.incrementAndGet()), "hhz6" + counter.get());
        String jsonstr = JSON.toJSONString(userContent);

        System.out.println("发送消息:" + jsonstr);

        Message message = new Message("hhz6", "hhz6-tag", jsonstr.getBytes(RemotingHelper.DEFAULT_CHARSET));

        SendResult result = hhz6Produder.send(message);
        System.err.println("发送响应：MsgId:" + result.getMsgId() + "，发送状态:" + result.getSendStatus());
    }
}
