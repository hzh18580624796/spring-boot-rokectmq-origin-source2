package com.hzh.app.javalock;
public class TTTT {

    private volatile String name;

    public void tt() {
        synchronized (this) {
            System.out.println("tt");
            name = new String("name");
        }
    }

    public synchronized void tt2() {
        name = new String("name");

    }

    public static void main(String[] args) {

    }
}
