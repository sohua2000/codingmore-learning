package top.codingmore.codingmorehelloword.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class RunableCreateTest {

    private static ExecutorService executors0 = Executors.newFixedThreadPool(3);

    public static void main(String[] args) throws InterruptedException {
        MyRunnable runnable = new MyRunnable();
        new Thread(runnable).start();

        for (int i = 0; i < 20; i++) {
            executors0.submit(new MyRunnable());
        }

        Thread.sleep(3000);
        executors0.shutdown();
    }
}

class MyRunnable implements Runnable {
    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + "\t" + Thread.currentThread().getId());
    }
}