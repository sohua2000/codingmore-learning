package top.codingmore.codingmorehelloword.demo3;

import java.util.concurrent.locks.ReentrantLock;

/**
 * Description: ReentrantLock 可重入锁, 同一个线程可以多次获得锁对象
 */
//@Slf4j(topic = "z.ReentrantTest")
//@Slf4j
public class ReentrantTest {
//    static final Logger log = LoggerFactory.getLogger(ReentrantTest.class);

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
