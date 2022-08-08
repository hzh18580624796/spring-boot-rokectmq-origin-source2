package com.hzh.springbootrokectmqoriginsource.mq.hhz6;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

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
public class Hhz6Content {
    private String username;
    private String pwd;

}
