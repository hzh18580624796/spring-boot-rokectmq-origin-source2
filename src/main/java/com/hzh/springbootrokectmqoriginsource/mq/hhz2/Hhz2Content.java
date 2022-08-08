package com.hzh.springbootrokectmqoriginsource.mq.hhz2;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;
import org.apache.rocketmq.client.impl.consumer.ConsumeMessageConcurrentlyService;
import org.apache.rocketmq.remoting.common.RemotingHelper;

import java.util.Collections;

/**
 * @Author 18011618
 * @Date 19:28 2018/7/17
 * @Function 发送消息体
 */
@ToString
@AllArgsConstructor
@EqualsAndHashCode
@Accessors(chain = true)
@Getter
@Setter
public class Hhz2Content {
    private String username;
    private String pwd;

}
