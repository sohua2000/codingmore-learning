package top.codingmore.codingmorehelloword.thread;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class ThreadPoolExecutorInvokeAllTest {
    static class MyTask implements Callable<Boolean> {

        private int id;

        public MyTask(int id) {
            this.id = id;
        }

        @Override
        public Boolean call() throws Exception {
            try {
                System.out.println(
                        "time:" + System.currentTimeMillis() + " " + Thread.currentThread() + " execute task " + id +
                                " start");
//                Thread.sleep(new Random().nextInt(4001));
                Thread.sleep(1999);

                System.out.println(
                        "time:" + System.currentTimeMillis() + " " + Thread.currentThread() + " execute task " + id +
                                " finish");
            } catch (InterruptedException e) {
                e.printStackTrace();
                return false;
            }
            return true;
        }
    }

    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        ThreadPoolExecutor executor =
                new ThreadPoolExecutor(10, 40, 5000,
                        TimeUnit.MILLISECONDS, new LinkedBlockingQueue<Runnable>(2));
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());

        List<Callable<Boolean>> tasks = new ArrayList<>();
        for (int i = 1; i <= 5; i++) {
            tasks.add(new MyTask(i));
        }
        List<Future<Boolean>> futures = null;
        try {
            //给定一组任务，在所有任务执行完成时返回一个 Futures 列表，其中包含它们的状态和结果。
            futures = executor.invokeAll(tasks,2,TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //主线程会等待 invokeAll 方法中的任务执行完后才继续执行
        //一般在使用 invokeAll 方法时建议加上等待时间，防止任务执行时间过长线程一直阻塞
        System.out.println("time:" + System.currentTimeMillis() + " 主线程会等待invokeAll执行完成才继续执行");
        for (int i = 0; i < futures.size(); i++) {
            try {
                System.out.println(futures.get(i).get());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }
        executor.shutdown();
        System.out.println("耗时：" + (System.currentTimeMillis() - start));
    }
}
