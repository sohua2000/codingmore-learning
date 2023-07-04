package top.codingmore.codingmorehelloword.future;

import java.util.concurrent.*;

public class FutureTest {
    public static void main(String[] args) {
        //创建线程池
        ExecutorService executor = Executors.newCachedThreadPool();//该线程池 keepAliveTime 为60秒，所以在60秒后如果线程空闲则会被回收
        //线程池提交任务异步任务(接收 Callable 对象)  doSomeLongComputation
        Future<String> future = executor.submit(new Callable<String>() {
//        Future<String> future = executor(new Callable<String>() {
            public String call() {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                return Thread.currentThread().getName() + " 异步任务结果";
            }
        });
        //doSomethingElse 在异步任务执行时，主线程可以执行其他操作
        System.out.println(Thread.currentThread().getName() + "线程打印。。。");
        //获取异步操作的结果，如果最终被阻塞，无法得到结果，那么在最多等待1秒钟之后退出
        try {
            String result = future.get(1, TimeUnit.SECONDS);
            System.out.println(result);
        } catch (ExecutionException ee) {
            // 计算抛出一个异常
            ee.printStackTrace();
        } catch (InterruptedException ie) {
            // 当前线程在等待过程中被中断
            ie.printStackTrace();
        } catch (TimeoutException te) {
            // 在Future对象完成之前超过已过期
            te.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + "执行结束");
//        executor.shutdown();//该线程池 keepAliveTime 为60秒，所以在60秒后如果线程空闲则会被回收
    }
}
