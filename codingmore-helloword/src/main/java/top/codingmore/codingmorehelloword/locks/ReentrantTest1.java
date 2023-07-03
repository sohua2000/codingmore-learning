package top.codingmore.codingmorehelloword.locks;

import java.util.concurrent.locks.ReentrantLock;

public class ReentrantTest1 {

    private static ReentrantLock lock = new ReentrantLock();

    public static void main(String[] args) throws InterruptedException {

        Thread thread = new Thread(() -> {
            try {
                // 如果没有竞争那么此方法就会获取 lock 对象锁
                // 如果有竞争就进入阻塞队列，可以被其它线程用 interruput 方法打断
                System.out.println("尝试获得锁");
                lock.lockInterruptibly();
            } catch (InterruptedException e) {
                e.printStackTrace();
                System.out.println("t1线程没有获得锁，被打断...return");
                return;
            }

            try {
                System.out.println("t1线程获得了锁");
            } finally {
                lock.unlock();
            }
        }, "t1");

        // t1启动前 主线程先获得了锁
        lock.lock();
        thread.start();
        Thread.sleep(3000);
        System.out.println("interrupt...打断t1");
        thread.interrupt();
    }

}
