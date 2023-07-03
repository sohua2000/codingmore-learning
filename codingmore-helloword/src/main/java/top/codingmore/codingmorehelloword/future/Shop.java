package top.codingmore.codingmorehelloword.future;

import java.util.Random;

public class Shop {

    private static final Random random = new Random();

    public double calculatePrice(String product) {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return random.nextDouble() * product.charAt(0);
    }

    public double getPrice(String product) {
        System.out.println(Thread.currentThread() + " 开始计算商品价格...");
        return calculatePrice(product);
    }

    public static void getPriceTest(){
        long start = System.currentTimeMillis();
        System.out.println(new Shop().getPrice("apple"));
        System.out.println(Thread.currentThread()+" 开始做其他事情。。。");
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("耗时："+(System.currentTimeMillis()-start));
    }


    public static void main(String[] args) {
        getPriceTest();
    }
}
