package com.hzh.app.javalock;

import java.util.concurrent.atomic.AtomicInteger;

public class ThreadLocal0x61c88647 {

    /**
     * 定义数组的初始大小
     */
    private static final int INITIAL_CAPACITY = 16;
    /**
     * 魔数 -> 可以让生成出来的值或者说ThreadLocal的Index均匀的分布在2^n的数组大小中
     */
    private static final int HASH_INCREMENT = 0x61c88647;
    /**
     * 魔数
     */
    private final int threadLocalHashCode = nextHashCode();
    /**
     * 定义一个线程安全的原子类AtomicInteger，用于魔数的累加
     */
    private static AtomicInteger nextHashCode = new AtomicInteger();

    /**
     * 计算下一个code(魔数累加)
     */
    private static int nextHashCode() {
        return nextHashCode.getAndAdd(HASH_INCREMENT);
    }

    /**
     * 根据生成的均匀分布的随机数threadLocalHashCode 取模(%) （数组大小INITIAL_CAPACITY-1（因为数组索引从0开始）)
     *
     * @return
     */
    public int index() {
        return this.threadLocalHashCode & (INITIAL_CAPACITY - 1);
    }

    public static void main(String[] args) {
        // 输出16次,模拟ThreadLocal中的默认初始大小
        for (int i = 0; i < 16; i++) {
            ThreadLocal0x61c88647 demo = new ThreadLocal0x61c88647();
            System.out.println(demo.index());
        }
    }
}
