package top.codingmore.codingmorehelloword;

//import com.github.benmanes.caffeine.cache.Cache;
//import com.github.benmanes.caffeine.cache.Caffeine;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import lombok.SneakyThrows;
import org.apache.juli.logging.Log;
import org.apache.juli.logging.LogFactory;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.function.IntBinaryOperator;
import java.util.stream.IntStream;

public class TestClass1 {
    static final Log log = LogFactory.getLog(TestClass1.class);
    static final Logger logger = LoggerFactory.getLogger(TestClass1.class);

    @Test
    public void test22() {
        // IntStream流
        IntStream intStream = IntStream.of(1, 1, 1, 1, 1, 1);

        // 1.设置初始值为10,(求和则会在10的基础上进行计算,计算结果为16,返回值类型为int)
        // 基础写法
        int reduce = intStream.reduce(10, new IntBinaryOperator() {
            @Override
            public int applyAsInt(int left, int right) {
                return left + right;
            }
        });
        System.out.println(reduce);    // 16

        // 简写
        IntStream intStream1 = IntStream.of(1, 1, 1, 1, 1, 1);
        int reduce1 = intStream1.reduce(10, Integer::sum);
        System.out.println(reduce1);    // 16

        // 2.无初始值(因为返回值可能为空,所以返回类型为OptionInt)
        // 因为没有初始值,int类型默认为0,所以计算结果为6
        IntStream intStream2 = IntStream.of(1, 1, 1, 1, 1, 1);
        OptionalInt reduce2 = intStream2.reduce(Integer::sum);
        System.out.println(reduce2.getAsInt());    // 6
    }

    @Test
    public void test23() {
        // iterator:迭代器
        IntStream intStream = IntStream.of(1, 9, 4, 35, 5, 2);
        PrimitiveIterator.OfInt iterator = intStream.iterator();
        while (iterator.hasNext()) {
            Integer next = iterator.next();
            System.out.println("iterator忌:" + next);
        }
    }


    @Test
    public void test24() {
        List<String> list = new ArrayList<>();
        Collections.addAll(list,"张无忌","周芷若","杨逍","张强","张三丰","赵敏");
        /**
         * 需求：
         *   1.拿到所有姓"张"的名字
         *   2.拿到长度为3个字的名字
         *   3.将最终结果进行打印
         */
        //JDK8 以前遍历操作集合
        /*****************多次for循环********************/
        //1.1 拿到所有行"张"的名字
        List<String> zhangList = new ArrayList<>();
        for (String name : list) {
            if(name.startsWith("张")){
                zhangList.add(name);
            }
        }
        //1.2.拿到长度为3个字的名字
        List<String> threeList = new ArrayList<>();
        for (String name : zhangList) {
            if(name.length()==3){
                threeList.add(name);
            }
        }
        //1.3.将最终结果进行打印
        for(String name : threeList){
            System.out.println(name);
        }
        /*******************2.一次for循环********************/
        for (String name : list) {
            if(name.startsWith("张") && name.length() == 3){
                System.out.println(name);
            }
        }

        //JDK8 以后使用Stream()流遍历操作集合
        /**************3.使用 Stream流来操作******************/
        list.stream()
                .filter(name->name.startsWith("张"))
                .filter(name->name.length()==3)
                .forEach(name-> System.out.println(name));

    }
    @Test
    public void test25() {
        List<String> list = new ArrayList<>();
        Collections.addAll(list,"Mary","Lucy","James","Johson","Steve");
        //forEach()遍历
        //未简写
        //list.forEach((String str)->{
        //    System.out.println(str);
        //});
        //简写1
        //list.forEach(str-> System.out.println(str));
        //最终简写
        list.forEach(System.out::println);

    }
    @Test
    public void test26() {
        List<String> list = new ArrayList<>();
        Collections.addAll(list,"Mary","Lucy","James","Johson","Steve");
        //count()计算集合中元素个数
        long count = list.stream().count();
        System.out.println("元素个数为:"+count);

        //filter()过滤,返回以"J"开头的名字
        list.stream().filter(str->str.startsWith("J")).forEach(System.out::println);

        //limit()截取,截取list集合前三个元素
        list.stream().limit(3).forEach(System.out::println);

    }
    @Test
    public void test27() {
        List<String> list = new ArrayList<>();
        Collections.addAll(list,"11","22","33","44","55");
        //通过map()方法,可以将String类型的流转换为int类型的流
        /*list.stream().map((String str)->{
              return Integer.parseInt(str);
          }).forEach((Integer num) -> {
              System.out.println(num);
          });*/
        //简化:
        //list.stream().map(str->Integer.parseInt(str)).forEach(str->System.out.println(str));
        //简化后:
        list.stream().map(Integer::parseInt).forEach(System.out::println);

    }
    @Test
    public void test28() {
        //CountDownLatch countDownLatch = new CountDownLatch(6);
        for (int i = 0; i < 6; i++) {
            new Thread(
                    ()->{//表示六个人开会
                System.out.println(Thread.currentThread().getName()+"\t到会议室了");
                //countDownLatch.countDown();
            },String.valueOf(i)
            ).start();
        }
        //countDownLatch.await();
        System.out.println(Thread.currentThread().getName()+"\t开始开会");


    }
    @SneakyThrows
    @Test
    public void test29() {
        //倒数门闩 它的作用是多个线程做汇聚
        //主线程开启了 A、B、C 三个线程做不同的事情，但是主线程需要等待 A、B、C 三个线程全部完成后才能继续后面的步骤。此时就需要 CountDownLatch 出马了。
        // CountDownLatch 会阻塞主线程，直到计数走到 0，门闩才会打开，主线程继续执行。
        CountDownLatch countDownLatch = new CountDownLatch(6);
        for (int i = 0; i < 6; i++) {
            new Thread(
                    ()->{//表示六个人开会
                        System.out.println(Thread.currentThread().getName()+"\t到会议室了");
                        countDownLatch.countDown();
                    },String.valueOf(i)
            ).start();
        }
        countDownLatch.await();
        System.out.println(Thread.currentThread().getName()+"\t开始开会");
        System.out.println("全部完成任务，主线程继续执行。。。");

    }
    @Test
    public void test30() {
        Cache<String, String> cache = Caffeine.newBuilder()
                //设置过期时间；向缓存中写入的数据会在1分钟之后过期
                .expireAfterWrite(1, TimeUnit.MINUTES)
                //设置最大值；最大可以放1条数据,当数据量超过最大值之后，则进行覆盖
                .maximumSize(1)
                .build();
        cache.put("c1", "c1");

        String k = "key";

        //获取缓存值，如果为空，返回null
        logger.info("cacheTest present： [{}] -> [{}]", k, cache.getIfPresent(k));
        //获取返回值，如果为空，则运行后面表达式，存入该缓存
        logger.info("cacheTest default： [{}] -> [{}]", k, cache.get(k, this::buildLoader));
        logger.info("cacheTest present： [{}] -> [{}]", k, cache.getIfPresent(k));
        //清除缓存
        cache.invalidate(k);
        logger.info("cacheTest present： [{}] -> [{}]", k, cache.getIfPresent(k));
    }
    private String buildLoader(String k) {
        return k + "+default";
    }
    @Test
    public void test31() {
    }
    @Test
    public void test32() {
    }
    @Test
    public void test33() {
    }

}
