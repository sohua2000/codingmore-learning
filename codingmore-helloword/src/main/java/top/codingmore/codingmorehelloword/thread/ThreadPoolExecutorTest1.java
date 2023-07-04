package top.codingmore.codingmorehelloword.thread;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

public class ThreadPoolExecutorTest1 {

    static class MyThreadFactory implements ThreadFactory {
        private final AtomicInteger threadNumber = new AtomicInteger(1);
        private final String namePrefix = "myPool-thread-";

        MyThreadFactory() {
        }

        public Thread newThread(Runnable r) {
            Thread t = new Thread(r, namePrefix + threadNumber.getAndIncrement());
            t.setDaemon(false);
            return t;
        }
    }

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
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(2, 4,
                0L, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<Runnable>(2),
                new MyThreadFactory(),
//                new ThreadPoolExecutor.AbortPolicy() //拒绝策略,最后2个抛出异常rejectedExecution
//         new ThreadPoolExecutor.DiscardPolicy() //丢弃策略，由 rejectedExecution 方法可知该策略不做任何处理
        new ThreadPoolExecutor.DiscardOldestPolicy() //丢弃策略，丢弃任务队列中最早的没被处理的任务 这里丢弃3，4，因为1，2正在处理
        );

        for (int i = 1; i <= 8; i++) {
            try {
                Thread.sleep(5);
//                threadPoolExecutor.execute(new MyTask(i));
                threadPoolExecutor.submit(new MyTask(i));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        threadPoolExecutor.shutdown();
    }
}
