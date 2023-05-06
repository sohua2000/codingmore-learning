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
        // IntStream��
        IntStream intStream = IntStream.of(1, 1, 1, 1, 1, 1);

        // 1.���ó�ʼֵΪ10,(��������10�Ļ����Ͻ��м���,������Ϊ16,����ֵ����Ϊint)
        // ����д��
        int reduce = intStream.reduce(10, new IntBinaryOperator() {
            @Override
            public int applyAsInt(int left, int right) {
                return left + right;
            }
        });
        System.out.println(reduce);    // 16

        // ��д
        IntStream intStream1 = IntStream.of(1, 1, 1, 1, 1, 1);
        int reduce1 = intStream1.reduce(10, Integer::sum);
        System.out.println(reduce1);    // 16

        // 2.�޳�ʼֵ(��Ϊ����ֵ����Ϊ��,���Է�������ΪOptionInt)
        // ��Ϊû�г�ʼֵ,int����Ĭ��Ϊ0,���Լ�����Ϊ6
        IntStream intStream2 = IntStream.of(1, 1, 1, 1, 1, 1);
        OptionalInt reduce2 = intStream2.reduce(Integer::sum);
        System.out.println(reduce2.getAsInt());    // 6
    }

    @Test
    public void test23() {
        // iterator:������
        IntStream intStream = IntStream.of(1, 9, 4, 35, 5, 2);
        PrimitiveIterator.OfInt iterator = intStream.iterator();
        while (iterator.hasNext()) {
            Integer next = iterator.next();
            System.out.println("iterator��:" + next);
        }
    }


    @Test
    public void test24() {
        List<String> list = new ArrayList<>();
        Collections.addAll(list,"���޼�","������","����","��ǿ","������","����");
        /**
         * ����
         *   1.�õ�������"��"������
         *   2.�õ�����Ϊ3���ֵ�����
         *   3.�����ս�����д�ӡ
         */
        //JDK8 ��ǰ������������
        /*****************���forѭ��********************/
        //1.1 �õ�������"��"������
        List<String> zhangList = new ArrayList<>();
        for (String name : list) {
            if(name.startsWith("��")){
                zhangList.add(name);
            }
        }
        //1.2.�õ�����Ϊ3���ֵ�����
        List<String> threeList = new ArrayList<>();
        for (String name : zhangList) {
            if(name.length()==3){
                threeList.add(name);
            }
        }
        //1.3.�����ս�����д�ӡ
        for(String name : threeList){
            System.out.println(name);
        }
        /*******************2.һ��forѭ��********************/
        for (String name : list) {
            if(name.startsWith("��") && name.length() == 3){
                System.out.println(name);
            }
        }

        //JDK8 �Ժ�ʹ��Stream()��������������
        /**************3.ʹ�� Stream��������******************/
        list.stream()
                .filter(name->name.startsWith("��"))
                .filter(name->name.length()==3)
                .forEach(name-> System.out.println(name));

    }
    @Test
    public void test25() {
        List<String> list = new ArrayList<>();
        Collections.addAll(list,"Mary","Lucy","James","Johson","Steve");
        //forEach()����
        //δ��д
        //list.forEach((String str)->{
        //    System.out.println(str);
        //});
        //��д1
        //list.forEach(str-> System.out.println(str));
        //���ռ�д
        list.forEach(System.out::println);

    }
    @Test
    public void test26() {
        List<String> list = new ArrayList<>();
        Collections.addAll(list,"Mary","Lucy","James","Johson","Steve");
        //count()���㼯����Ԫ�ظ���
        long count = list.stream().count();
        System.out.println("Ԫ�ظ���Ϊ:"+count);

        //filter()����,������"J"��ͷ������
        list.stream().filter(str->str.startsWith("J")).forEach(System.out::println);

        //limit()��ȡ,��ȡlist����ǰ����Ԫ��
        list.stream().limit(3).forEach(System.out::println);

    }
    @Test
    public void test27() {
        List<String> list = new ArrayList<>();
        Collections.addAll(list,"11","22","33","44","55");
        //ͨ��map()����,���Խ�String���͵���ת��Ϊint���͵���
        /*list.stream().map((String str)->{
              return Integer.parseInt(str);
          }).forEach((Integer num) -> {
              System.out.println(num);
          });*/
        //��:
        //list.stream().map(str->Integer.parseInt(str)).forEach(str->System.out.println(str));
        //�򻯺�:
        list.stream().map(Integer::parseInt).forEach(System.out::println);

    }
    @Test
    public void test28() {
        //CountDownLatch countDownLatch = new CountDownLatch(6);
        for (int i = 0; i < 6; i++) {
            new Thread(
                    ()->{//��ʾ�����˿���
                System.out.println(Thread.currentThread().getName()+"\t����������");
                //countDownLatch.countDown();
            },String.valueOf(i)
            ).start();
        }
        //countDownLatch.await();
        System.out.println(Thread.currentThread().getName()+"\t��ʼ����");


    }
    @SneakyThrows
    @Test
    public void test29() {
        //�������� ���������Ƕ���߳������
        //���߳̿����� A��B��C �����߳�����ͬ�����飬�������߳���Ҫ�ȴ� A��B��C �����߳�ȫ����ɺ���ܼ�������Ĳ��衣��ʱ����Ҫ CountDownLatch �����ˡ�
        // CountDownLatch ���������̣߳�ֱ�������ߵ� 0�����ŲŻ�򿪣����̼߳���ִ�С�
        CountDownLatch countDownLatch = new CountDownLatch(6);
        for (int i = 0; i < 6; i++) {
            new Thread(
                    ()->{//��ʾ�����˿���
                        System.out.println(Thread.currentThread().getName()+"\t����������");
                        countDownLatch.countDown();
                    },String.valueOf(i)
            ).start();
        }
        countDownLatch.await();
        System.out.println(Thread.currentThread().getName()+"\t��ʼ����");
        System.out.println("ȫ������������̼߳���ִ�С�����");

    }
    @Test
    public void test30() {
        Cache<String, String> cache = Caffeine.newBuilder()
                //���ù���ʱ�䣻�򻺴���д������ݻ���1����֮�����
                .expireAfterWrite(1, TimeUnit.MINUTES)
                //�������ֵ�������Է�1������,���������������ֵ֮������и���
                .maximumSize(1)
                .build();
        cache.put("c1", "c1");

        String k = "key";

        //��ȡ����ֵ�����Ϊ�գ�����null
        logger.info("cacheTest present�� [{}] -> [{}]", k, cache.getIfPresent(k));
        //��ȡ����ֵ�����Ϊ�գ������к�����ʽ������û���
        logger.info("cacheTest default�� [{}] -> [{}]", k, cache.get(k, this::buildLoader));
        logger.info("cacheTest present�� [{}] -> [{}]", k, cache.getIfPresent(k));
        //�������
        cache.invalidate(k);
        logger.info("cacheTest present�� [{}] -> [{}]", k, cache.getIfPresent(k));
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
