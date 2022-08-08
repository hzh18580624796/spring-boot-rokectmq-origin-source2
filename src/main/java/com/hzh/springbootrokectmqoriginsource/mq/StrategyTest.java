package com.hzh.springbootrokectmqoriginsource.mq;

import org.apache.rocketmq.client.consumer.AllocateMessageQueueStrategy;
import org.apache.rocketmq.client.consumer.rebalance.AllocateMessageQueueAveragely;
import org.apache.rocketmq.client.consumer.rebalance.AllocateMessageQueueAveragelyByCircle;
import org.apache.rocketmq.common.message.MessageQueue;

import java.util.ArrayList;
import java.util.List;

public class StrategyTest {
    public static void allocate(AllocateMessageQueueStrategy strategy, int mqSize, int cidSize) {
        List<String> cidList = new ArrayList<String>();
        for (int i = 0; i < cidSize; i++) {
            cidList.add("cid_" + i);
        }
        List<MessageQueue> mqList = new ArrayList<MessageQueue>(mqSize);
        for (int i = 0; i < mqSize; i++) {
            mqList.add(makeMq(i));
        }
        for (int i = 0; i < cidSize; i++) {
            doTest(strategy, i, cidList, mqList);
        }
        System.out.println("-----");
    }
    private static void doTest(AllocateMessageQueueStrategy strategy, int currentCid, List<String> cidList, List<MessageQueue> mqList) {
        List<MessageQueue> result = strategy.allocate("group","cid_" + currentCid, mqList, cidList);
        List<Integer> queueIdList = new ArrayList<>(result.size());
        for (MessageQueue mq : result) {
            queueIdList.add(mq.getQueueId());
        }
        System.out.println("cid: " + currentCid + " queueId: " + queueIdList);
    }
    private static MessageQueue makeMq(int queueId) {
        MessageQueue q = new MessageQueue();
        q.setTopic("topic_a");
        q.setBrokerName("broker_a");
        q.setQueueId(queueId);
        return q;
    }
    public static void main(String[] args) {
        test(new AllocateMessageQueueAveragely());
        test(new AllocateMessageQueueAveragelyByCircle());
    }
    private static void test(AllocateMessageQueueStrategy strategy) {
        System.out.println(strategy.getClass().getSimpleName());
        System.out.println(" ");
        StrategyTest.allocate(strategy, 3, 2);
        StrategyTest.allocate(strategy, 3, 3);
        StrategyTest.allocate(strategy, 3, 4);
        StrategyTest.allocate(strategy, 3, 5);
        System.out.println("|||||");
    }
}
