package top.codingmore.codingmorehelloword.thread;

import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.ThreadPoolExecutor;

public class ThreadPoolTaskExecutorTest {

    static class MyTask implements Runnable {

        private int i;

        public MyTask(int i) {
            this.i = i;
        }

        @Override
        public void run() {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread() + " " + i);
        }
    }

    public static void main(String[] args) {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        //设置核心线程数
        executor.setCorePoolSize(2);
        //设置最大线程数
        executor.setMaxPoolSize(4);
        //设置线程被回收的空闲时长
        executor.setKeepAliveSeconds(2);
        //设置队列容量
        executor.setQueueCapacity(2);
        //设置线程前缀
        executor.setThreadNamePrefix("tt-");
        //设置拒绝策略
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.AbortPolicy());
        //初始化线程池
        executor.initialize();
        for (int i = 1; i <= 8; i++) {
            try {
                System.out.println("s---"+i);
                executor.execute(new MyTask(i));
//                System.out.println("e------"+i);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
//        executor.shutdown();
    }
}
