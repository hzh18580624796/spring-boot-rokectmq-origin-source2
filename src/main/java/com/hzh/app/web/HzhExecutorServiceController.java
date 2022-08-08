package com.hzh.app.web;

import com.hzh.app.config.id.IdWorker;
import com.hzh.app.threadPool.HzhExecutorServiceWrapper;
import com.hzh.app.threadPool.util.MDCThreadLocalUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

@Slf4j
@RestController
public class HzhExecutorServiceController {

    @Autowired
    private HzhExecutorServiceWrapper hzhExecutorServiceWrapper;
    @Autowired
    private IdWorker idWorker;

    @GetMapping("/hzhExecutorServiceRunable")
    public void hzhExecutorServiceRunable(HttpServletRequest request) throws ExecutionException, InterruptedException {
        Future<String> future = hzhExecutorServiceWrapper.submit(() -> {

            System.out.println("runable runing");
        }, "yes");

        System.out.println(future.get());
    }


    @GetMapping("/hzhExecutorServiceCallble")
    public void hzhExecutorServiceCallble() throws ExecutionException, InterruptedException {

        log.info("HzhExecutorServiceController hzhExecutorServiceCallble");

        List<Future<String>> futures = new ArrayList<>();
        for (int i = 0; i < 10; i++) {

            MDCThreadLocalUtil.setTid2AndApply(() -> {
                Future<String> future = hzhExecutorServiceWrapper.submit(() -> {

                    System.out.println("callble runing");
                    return "callble-" + idWorker.nextIdNo();
                });
                futures.add(future);
            });
        }

        for (Future<String> future : futures) {
            log.info("future.get()={}", future.get());
        }
    }

}
