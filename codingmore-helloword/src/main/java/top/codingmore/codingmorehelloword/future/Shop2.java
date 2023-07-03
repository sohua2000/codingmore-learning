package top.codingmore.codingmorehelloword.future;

import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public class Shop2 {

    private static final Random random = new Random();

    public double calculatePrice(String product) {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if (product== null || "".equals(product)) {
            throw new RuntimeException("无效的商品");
        }
        return random.nextDouble() * product.charAt(0);
    }


    public Future<Double> getPriceAsync(String product) {
        //创建 CompletableFuture 对象，对象中包含异步计算结果
        CompletableFuture<Double> futurePrice = new CompletableFuture<>();
        //新建线程计算商品价格
        new Thread(() -> {
            try{
                double price = calculatePrice(product);
                //将异步计算得到的结果设置到 CompletableFuture 中，
                futurePrice.complete(price);
            } catch (Exception e){
                futurePrice.completeExceptionally(e);
            }

        }).start();
        futurePrice.exceptionally(ex -> {
            System.out.println("exceptionally =====");
            return null;
        });
        //无需等待计算结果，直接返回 CompletableFuture 对象
        return futurePrice;
    }

    public static void getPriceAsyncTest(String product){
        long start = System.currentTimeMillis();
        Future<Double> priceFuture = new Shop2().getPriceAsync(product);
        System.out.println(Thread.currentThread()+" 开始做其他事情。。。");
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        try {

            System.out.println(priceFuture.get());;
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        System.out.println("耗时："+(System.currentTimeMillis()-start));
    }

    public static void main(String[] args) {
        String prod = null;
//        String prod = "8";
        getPriceAsyncTest(prod);
    }


}
