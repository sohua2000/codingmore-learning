package top.codingmore;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

@SpringBootTest
public class JedisApplicationTests {
    @Autowired
    private JedisPool jedisPool;

    @Test
    public void contextLoads() {
        //https://blog.csdn.net/wenkezhuangyuan/article/details/119430545
        System.out.println(jedisPool);
        //在连接池中得到Jedis连接
        Jedis jedis = jedisPool.getResource();
        jedis.set("haha", "你好");
        jedis.set("name", "wangpengcheng");

        System.out.println("haha="+jedis.get("haha"));
        System.out.println("name="+jedis.get("name"));

        //获取锁推荐使用set的方式，返回值result ：设置成功，返回 1 。设置失败，返回 0

        String lockKey = "lockKey";
        String requestId = "requestId";
        Long seconds = Long.valueOf(10);//缓存过期时间
        Long result = jedis.setnx(lockKey,requestId);
        Long result2 = jedis.setnx(lockKey,requestId);
        if (seconds >= 0) {
            jedis.expire(lockKey, seconds);
        }
        System.out.println("result="+result);
        System.out.println("result2="+result2);

        try {
            Thread.sleep((seconds-0)*1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Long result3 = jedis.setnx(lockKey,requestId);
        if (seconds >= 0) {
            jedis.expire(lockKey, seconds);
        }
        System.out.println("result3="+result3);



        //关闭当前连接
        jedis.close();

    }
}