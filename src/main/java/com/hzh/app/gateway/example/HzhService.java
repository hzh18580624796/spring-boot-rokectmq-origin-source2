package com.hzh.app.gateway.example;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Random;

@Slf4j
@Component
public class HzhService {

    public String tt(String in) {

        return in + "-response by server";
    }

    public Integer add(Integer in) {
        int random = new Random().nextInt(10);
        log.info("random={}", random);
        return in + random;
    }

    public static void main(String[] args) {
        Random random = new Random();
        for (int i = 0; i < 10; i++) {
            System.out.println(random.nextInt(10));
        }

    }
}
