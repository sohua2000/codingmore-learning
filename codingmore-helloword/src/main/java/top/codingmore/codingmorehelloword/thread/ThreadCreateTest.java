package top.codingmore.codingmorehelloword.thread;


import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadCreateTest {
    private static ExecutorService executors = Executors.newFixedThreadPool(2);

    private static ExecutorService executors2 = Executors.newFixedThreadPool(3);

    public static void main(String[] args) throws InterruptedException {


        new MyThread().start();
        System.out.println("-----------------");
//        new MyThread().run();
        for (int i = 0; i < 20; i++) {
            executors.submit(new MyThread());
        }


        Thread.sleep(2000);
        System.out.println("-----------------");


        for (int i = 0; i < 20; i++) {
            executors2.submit(()->{
                System.out.println(Thread.currentThread().getName() + "\t" + Thread.currentThread().getId());
            });
        }

        Thread.sleep(2000);
        executors.shutdown();
        executors2.shutdown();

    }
}

class MyThread extends Thread {
    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + "\t" + Thread.currentThread().getId());
    }
}
