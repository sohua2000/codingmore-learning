package top.codingmore.codingmorehelloword.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CachedThreadExecutorTest {

    static class MyTask implements Runnable {

        private int i;

        public MyTask(int i) {
            this.i = i;
        }

        @Override
        public void run() {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread() + " " + i);
        }
    }

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newCachedThreadPool();
        for (int i = 1; i <= 8; i++) {
            try {
//                executorService.execute(new MyTask(i));
                executorService.submit(new MyTask(i));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        executorService.shutdown();
    }
}
