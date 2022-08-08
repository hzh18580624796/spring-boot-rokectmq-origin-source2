package com.hzh.app.canal;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.otter.canal.protocol.CanalEntry;
import com.xpand.starter.canal.annotation.CanalEventListener;
import com.xpand.starter.canal.annotation.UpdateListenPoint;

/**
 * @author chen.qian
 * @date 2018/3/19
 */
@CanalEventListener
public class MyEventListener2 {

    @UpdateListenPoint
    public void onUpdate(CanalEntry.EventType eventType, CanalEntry.RowData rowData) {

        System.out.println("MyEventListener2 eventType= " + JSONObject.toJSONString(eventType));

        UserTmp userTmp = new UserTmp();

        rowData.getAfterColumnsList().forEach((ele) -> {
            if ("id".equals(ele.getName())) {
                userTmp.setId(ele.getValue());
            }
            if ("name".equals(ele.getName())) {
                userTmp.setName(ele.getValue());
            }
        });

        System.out.println("MyEventListener2 userTmp= " + JSONObject.toJSONString(userTmp));
    }
}
