package top.codingmore.codingmorehelloword.locks;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.locks.ReentrantLock;

/**
 * Description: ReentrantLock 可重入锁, 同一个线程可以多次获得锁对象
 *
 * 相对于synchronized 它具备如下特点
 * 非阻塞的竞争锁方法 tryLock() 方法
 * 可中断 lockInterruptibly()
 * 可以设置超时时间
 * 可以设置为公平锁
 * 支持多个条件变量
 * 与 synchronized 一样,都支持可重入
 */
@Slf4j(topic = "z.ReentrantTest")
public class ReentrantTest {

    private static ReentrantLock lock = new ReentrantLock();

    public static void main(String[] args) {
        // 如果有竞争就进入`阻塞队列`, 一直等待着,不能被打断
        // 主线程main获得锁
        lock.lock();
        try {
            System.out.println("entry main...");
            m1();
        } finally {
            lock.unlock();
        }
    }

    private static void m1() {
        lock.lock();
        try {
            System.out.println("entry m1...");
            m2();
        } finally {
            lock.unlock();
        }
    }

    private static void m2() {
        System.out.println("entry m2....");
    }
}
