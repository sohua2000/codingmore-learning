package top.codingmore.codingmorehelloword.thread;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class ThreadPoolExecutorInvokeAnyTest {
    static class MyTask implements Callable<String> {

        private int id;

        public MyTask(int id) {
            this.id = id;
        }

        @Override
        public String call() throws Exception {
            Integer randomTime = new Random().nextInt(3) * 1000;
            try {
                System.out.println(
                        "time:" + System.currentTimeMillis() + " " + Thread.currentThread() + " execute task " + id +
                                " start,random time :"+randomTime);
                Thread.sleep(randomTime);
                System.out.println(
                        "time:" + System.currentTimeMillis() + " " + Thread.currentThread() + " execute task " + id +
                                " finish");
            } catch (InterruptedException e) {
                e.printStackTrace();
                return "";
            }
            return "task" + id;
        }
    }

    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        ThreadPoolExecutor executor =
                new ThreadPoolExecutor(10, 40, 5000,
                        TimeUnit.MILLISECONDS, new LinkedBlockingQueue<Runnable>(2));
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());

        List<Callable<String>> tasks = new ArrayList<>();
        for (int i = 1; i <= 5; i++) {
            tasks.add(new MyTask(i));
        }
        String futureResult = null;
        try {
            //给定一组任务，只要有一个任务执行完成就返回这个任务的结果
            futureResult = executor.invokeAny(tasks, 3, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }
        System.out.println("time:" + System.currentTimeMillis() + " 主线程会等待invokeAny执行完成才继续执行");
        System.out.println("执行的任务为：" + futureResult);
        executor.shutdown();
        System.out.println("耗时：" + (System.currentTimeMillis() - start));
    }
}
