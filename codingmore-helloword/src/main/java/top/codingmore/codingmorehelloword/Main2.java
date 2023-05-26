package top.codingmore.codingmorehelloword;

import org.apache.juli.logging.Log;
import org.apache.juli.logging.LogFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import top.codingmore.codingmorehelloword.redis.RedisTest;

import java.util.stream.IntStream;

import static top.codingmore.codingmorehelloword.redis.RedisTest.test;

public class Main2 {
    static final Log log2 = LogFactory.getLog(Main.class);
    static final Logger logger = LoggerFactory.getLogger(Main2.class);


    public static void main(String[] args) {
        Log log = LogFactory.getLog(Main.class);
        log.info("start..2.");

        log.warn("end.");

        log2.info("foo");

        logger.info("slf4j-foo");

        testStream();

        test();


    }

    private static void testStream() {
        //Stream API中iterate方法的新重载方法，可以指定什么时候结束迭代
        IntStream intStream10 = IntStream.of(1,2,3,4,5,6,7);
        intStream10.forEach(System.out::println);

        IntStream intStream11 = IntStream.iterate(1,x->{
            int a = 2 * x;
//            System.out.println(a);
//            logger.info(String.valueOf(a));
            return a;
        }).limit(6);
        intStream11.forEach(System.out::println);

//        IntStream range = IntStream.rangeClosed(1, 100);
//        range.forEach(System.out::println);

//        IntStream generate1 = IntStream.generate(() -> new Random().nextInt(100));
//        generate1.forEach(System.out::println);
//        IntStream.iterate(1, i -> i < 100, i -> i + 1).forEach(System.out::println);
        IntStream intStream = IntStream.of(2,6,3,5,9,8,1).parallel();
        intStream.forEach(x->{
            System.out.println("forEach->"+x);
        });

        // forEachOrdered()同forEach()方法
        // 区别：在于并行流处理下,forEachOrdered会保证实际的处理顺序与流中元素的顺序一致
        // 而forEach方法无法保证,默认的串行流处理下,两者无区别,都能保证处理顺序与流中元素顺序一致
        IntStream intStream1 = IntStream.of(2,6,3,5,9,8,1).parallel();
        intStream1.forEachOrdered(x->{
            System.out.println("forEachOrdered->"+x);
        });

        //distinct() 方法，可以用来去除重复数据。因为 distinct() 方法是一个非终结方法，所以必须调用终止方法。
        IntStream intStream13 = IntStream.of(1, 2, 3, 4, 5, 6, 1, 4, 5, 2);
        intStream13.distinct().forEach(System.out::println);


    }
}
