package com.hzh.app.circulardependency;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Z {

    @Autowired
    private X x;

    public static void main(String[] args) {
        Long i = new Long(0);

        Long k = new Long(1);

        while (true) {
            i++;
            //fixme 如果k是 int的话，到21 0000 0000会溢出
            if (i == k * 1000000000) {
                k++;
                System.out.println(i);
            }
        }
    }
}

//int
//21 0000 0000
//21 4748 3647

//long
//922 3372 0368 5477 5807


