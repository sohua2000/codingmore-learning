package top.codingmore.codingmorehelloword;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.CountDownLatch;

//@RunWith(SpringRunner.class)
@SpringBootTest
public class ThreadPoolTaskExecutorTests {

    static class MyTask implements Runnable {

        private int i;

        private CountDownLatch countDownLatch;

        public MyTask(int i, CountDownLatch countDownLatch) {
            this.i = i;
            this.countDownLatch = countDownLatch;
        }

        @Override
        public void run() {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread() + " " + i);
            countDownLatch.countDown();
        }
    }

    @Autowired
    private ThreadPoolTaskExecutor taskExecutor;

    @Test
    public void contextLoads() {
        CountDownLatch countDownLatch = new CountDownLatch(8);
        long start = System.currentTimeMillis();
        for (int i = 1; i <= 8; i++) {
            try {
                taskExecutor.execute(new MyTask(i, countDownLatch));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("耗时：" + (System.currentTimeMillis() - start));
    }

}
