//package com.hzh.app.canal;
//
//import com.alibaba.otter.canal.protocol.CanalEntry;
//import io.github.tequilacn.starter.listener.CanalDmlRawEventListener;
//import org.springframework.stereotype.Component;
//
//import java.util.List;
//
//@Component
//public class HhzCanalDmlRawEventListener implements CanalDmlRawEventListener {
//    @Override
//    public void onInsert(List<CanalEntry.Column> list) {
//        System.out.println("onInsert");
//    }
//
//    @Override
//    public void onUpdate(List<CanalEntry.Column> list, List<CanalEntry.Column> list1) {
//        System.out.println("onUpdate");
//    }
//
//    @Override
//    public void onDelete(List<CanalEntry.Column> list) {
//        System.out.println("onDelete");
//    }
//}
