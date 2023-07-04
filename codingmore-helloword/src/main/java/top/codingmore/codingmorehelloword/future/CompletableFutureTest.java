package top.codingmore.codingmorehelloword.future;

import lombok.SneakyThrows;

import java.util.Date;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

public class CompletableFutureTest {

    @SneakyThrows
    public static void main(String[] args) {
        long l = System.currentTimeMillis();
        CompletableFuture<String> tokenFuture = CompletableFuture.supplyAsync(() -> {
            System.out.println("future 1: 第   " + (new Date().getTime() - l) + "  毫秒：" + Thread.currentThread().getName());
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "ABC";
        });
        CompletableFuture<String> signFuture = CompletableFuture.supplyAsync(() -> {
            System.out.println("future 2: 第   " + (new Date().getTime() - l) + "  毫秒：" + Thread.currentThread().getName());
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "DEF";
        });
        CompletableFuture.allOf(tokenFuture, signFuture).thenApplyAsync(x -> {
            System.out.println("future 3: 第 " + (new Date().getTime() - l) + " 毫秒：" + Thread.currentThread().getName());
            System.out.println(tokenFuture.join());
            System.out.println(signFuture.join());
            return "XYZ";
        });
        TimeUnit.SECONDS.sleep(3);
    }

    public static void main12(String[] args) {
        CompletableFuture<String> future = CompletableFuture.supplyAsync(()->{
            PrintTool.printTimeAndThread("compute test");
            return "test";
        });

        String result = future.join();
        PrintTool.printTimeAndThread("get result: " + result);
    }
    public static void main11(String[] args) {
        CompletableFuture<String> future = CompletableFuture.supplyAsync(()->{
            try{
                Thread.sleep(10000L);
                return "test";
            } catch (Exception e){
                return "failed test";
            }
        });
        future.complete("manual test");
//        System.out.println(future.join());
        try {
            System.out.println(future.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
}
