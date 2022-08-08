package com.hzh.app.aopproxy;

public class ProxyB extends B {

    private B target;

    public ProxyB(B b) {
        this.target = b;
    }

    public void b1() {
        target.b1();
    }

    public void b2() {
        target.b2();
    }
}
