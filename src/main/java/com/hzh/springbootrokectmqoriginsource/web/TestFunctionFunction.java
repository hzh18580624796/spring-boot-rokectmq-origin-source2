//package com.hzh.springbootrokectmqoriginsource.web;
//
//import lombok.Data;
//
//import java.util.function.Consumer;
//import java.util.function.Function;
//import java.util.function.Predicate;
//import java.util.function.Supplier;
//
//@Data
//public class TestFunctionFunction {
//
//    public static void main(String[] args) {
//        TestFunctionFunction test = new TestFunctionFunction();
//
//
//
//        hzh()
//    }
//
//    public static HzhContext hzh(HzhContext context) {
//
//        return context;
//    }
//
//    public static String t1(Function<String, String> function) {
//        String result = "1".concat("-").concat(function.apply("x"));
//        System.out.println(result);
//
//        return result;
//    }
//
//    public String execute(Function<String, String> function) {
//        String result = "2".concat("-").concat(function.apply("in"));
//        System.out.println(result);
//
//        return result;
//    }
//
//
//    public void run(Runnable... runnables) {
//        for (Runnable runnable : runnables) {
//            runnable.run();
//        }
//    }
//
//    @Data
//    public static class HzhContext {
//
//    }
//}
