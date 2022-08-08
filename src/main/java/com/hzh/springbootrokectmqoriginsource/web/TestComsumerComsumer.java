package com.hzh.springbootrokectmqoriginsource.web;

import lombok.Data;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

@Data
public class TestComsumerComsumer {

    public static void main(String[] args) {
        TestComsumerComsumer test = new TestComsumerComsumer();
//        String result = t1(x -> {
//            return test.execute(in -> {
//                return "2";
//            });
//        }, "");

        String result = t1(x -> test.execute(in -> test.execute2(out -> out)));

        System.out.println("最后的result= " + result);


        test.run(
                new Runnable() {
                    @Override
                    public void run() {
                        System.out.println("1");
                    }
                }, new Runnable() {
                    @Override
                    public void run() {
                        System.out.println("2");
                    }
                }, new Runnable() {
                    @Override
                    public void run() {
                        System.out.println("3");
                    }
                });


        String resultTT = tt(a -> tt(b -> tt(c -> tt(d -> tt(null)))));

        System.out.println(resultTT);

        Function function = null;
        Supplier supplier = null;
        Consumer consumer = null;
        Predicate predicat = null;
    }

    public static String tt(Function<String, String> function) {
        if (function == null) {
            return "-end";
        }
        String result = "1".concat("-").concat(function.apply(""));

        return result;
    }

    public static String t1(Function<String, String> function) {
        String result = "1".concat("-").concat(function.apply("x"));
        System.out.println(result);

        return result;
    }

    public String execute(Function<String, String> function) {
        String result = "2".concat("-").concat(function.apply("in"));
        System.out.println(result);

        return result;
    }

    public String execute2(Function<String, String> function) {
        String result = function.apply("ooo");
        System.out.println(result);

        return result;
    }

    public void execute2(Notifyer notifyer) {
        try {
            notifyer.onSuccess();
        } catch (Exception e) {
            notifyer.onFail();
        }
    }

    public void run(Runnable... runnables) {
        for (Runnable runnable : runnables) {
            runnable.run();
        }
    }
}
