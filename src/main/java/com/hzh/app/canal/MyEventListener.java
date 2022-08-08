package com.hzh.app.canal;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.otter.canal.protocol.CanalEntry;
import com.xpand.starter.canal.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author chen.qian
 * @date 2018/3/19
 */
@CanalEventListener
public class MyEventListener {

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @InsertListenPoint
    public void insert(CanalEntry.EventType eventType, CanalEntry.RowData rowData) {
        System.out.println("eventType= " + JSONObject.toJSONString(eventType));

        UserTmp userTmp = new UserTmp();
        rowData.getAfterColumnsList().forEach((ele) -> {
            System.out.println(ele);
            if ("id".equals(ele.getName())) {
                userTmp.setId(ele.getValue());
            }
            if ("name".equals(ele.getName())) {
                userTmp.setName(ele.getValue());
            }

        });

        redisTemplate.opsForValue().set("userTmp-" + userTmp.getId(), JSONObject.toJSONString(userTmp));
    }

    @UpdateListenPoint
    public void update(CanalEntry.EventType eventType, CanalEntry.RowData rowData) {
        System.out.println("eventType= " + JSONObject.toJSONString(eventType));

        UserTmp userTmp = new UserTmp();
        rowData.getAfterColumnsList().forEach((ele) -> {
            System.out.println(ele);
            if ("id".equals(ele.getName())) {
                userTmp.setId(ele.getValue());
            }
            if ("name".equals(ele.getName())) {
                userTmp.setName(ele.getValue());
            }

        });

        redisTemplate.opsForValue().set("userTmp-" + userTmp.getId(), JSONObject.toJSONString(userTmp));
    }

    @DeleteListenPoint
    public void delete(CanalEntry.EventType eventType, CanalEntry.RowData rowData) {
        System.out.println("eventType= " + JSONObject.toJSONString(eventType));

        UserTmp userTmp = new UserTmp();
        rowData.getBeforeColumnsList().forEach((ele) -> {
            System.out.println(ele);
            if ("id".equals(ele.getName())) {
                userTmp.setId(ele.getValue());
            }
            if ("name".equals(ele.getName())) {
                userTmp.setName(ele.getValue());
            }

        });

        redisTemplate.delete("userTmp-" + userTmp.getId());
    }

    @ListenPoint(destination = "example", schema = "canal-test", table = {"t_user", "test_table"}, eventType = CanalEntry.EventType.UPDATE)
    public void onEvent4(CanalEntry.EventType eventType, CanalEntry.RowData rowData) {
        System.err.println("DeleteListenPoint");
        rowData.getAfterColumnsList().forEach((c) -> System.err.println("By--Annotation: " + c.getName() + " ::   " + c.getValue()));
    }

    public static void main(String[] args) {
        Map<String, List<String>> store = new HashMap<>();

        for (int i = 0; i < 10; i++) {
            List<String> list = store.getOrDefault(i + "", new ArrayList<>());

            list.add(i + "");
            store.put(i + "", list);
        }

        System.out.println(store);
    }
}
