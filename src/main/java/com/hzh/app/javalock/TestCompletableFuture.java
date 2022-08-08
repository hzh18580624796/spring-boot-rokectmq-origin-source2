package com.hzh.app.javalock;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class TestCompletableFuture {

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        CompletableFuture.completedFuture("hzh");

        runAsyncExample();
        applyToEitherExample();
        runAfterBothExample();
        tt();
    }

    static void runAsyncExample() {
        CompletableFuture cf = CompletableFuture.runAsync(() -> {
            System.out.println("hzh");
        });
    }

    static void tt() throws ExecutionException, InterruptedException {
        CompletableFuture future = CompletableFuture.supplyAsync(() -> {
            System.out.println("hzh");
            return "s";
        });

        System.out.println(future.get());
    }

    static void applyToEitherExample() {
        String original = "Message";
        CompletableFuture cf1 = CompletableFuture.completedFuture(original)
                .thenApplyAsync(s -> s.toUpperCase());
        CompletableFuture cf2 = cf1.applyToEither(
                CompletableFuture.completedFuture(original).thenApplyAsync(s -> s.toLowerCase()),
                s -> s + " from applyToEither");
        System.out.println((((String) cf2.join()).endsWith(" from applyToEither")));
    }

    static void runAfterBothExample() throws ExecutionException, InterruptedException {
        String original = "Message";

        CompletableFuture<StringBuilder> future =
                CompletableFuture.completedFuture(original).thenApply(String::toUpperCase)
                        .thenCombine(
                                CompletableFuture.completedFuture(original).thenApply(String::toLowerCase),
                                (x, y) -> new StringBuilder().append(x).append(y));
        System.out.println(future.get());
    }
}
