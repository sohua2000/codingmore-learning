package top.codingmore.codingmorehelloword.locks;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

public class ReentrantTest2 {

    private static ReentrantLock lock = new ReentrantLock();

    public static void main(String[] args) throws InterruptedException {

        Thread t1 = new Thread(() -> {
            System.out.println("尝试获得锁");
//            // 此时肯定获取失败, 因为主线程已经获得了锁对象
//            if (!lock.tryLock()) {
//                System.out.println("获取立刻失败，返回");
//                return;
//            }
            try {
                // 等待1s
                if (!lock.tryLock(3, TimeUnit.SECONDS)) {
                    System.out.println("等待3s获取不到锁");
                    return;
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
                System.out.println("获取不到锁");
                return;
            }


            try {
                System.out.println("获得到锁");
            } finally {
                lock.unlock();
            }
        }, "t1");

        // 主线程先获得锁
        lock.lock();
        System.out.println("获得到锁");
        t1.start();
        // 主线程2s之后才释放锁
        Thread.sleep(5000);
        System.out.println("释放了锁");
        lock.unlock();
    }

}
