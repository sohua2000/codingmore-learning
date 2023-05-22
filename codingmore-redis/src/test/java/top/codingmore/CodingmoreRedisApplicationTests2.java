package top.codingmore;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import top.codingmore.redis.CommonRedisHelper;

import javax.annotation.Resource;

@SpringBootTest
class CodingmoreRedisApplicationTests2 {
    @Resource
    private RedisTemplate redisTemplate;

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @Test
    public void testRedis() {
        // 添加
        redisTemplate.opsForValue().set("name","沉默王二");
        // 查询
        System.out.println(redisTemplate.opsForValue().get("name"));
        // 删除
        redisTemplate.delete("name");
        // 更新
        redisTemplate.opsForValue().set("name","沉默王二的狗腿子");
        // 查询
        System.out.println(redisTemplate.opsForValue().get("name"));

        // 添加
        stringRedisTemplate.opsForValue().set("name","沉默王二");
        // 查询
        System.out.println(stringRedisTemplate.opsForValue().get("name"));
        // 删除
        stringRedisTemplate.delete("name");
        // 更新
        stringRedisTemplate.opsForValue().set("name","沉默王二的狗腿子");
        // 查询
        System.out.println(stringRedisTemplate.opsForValue().get("name"));


        String  key = "lockkey";
        CommonRedisHelper redisHelper = new CommonRedisHelper();
        boolean lock = redisHelper.lock(key);
        if (lock) {
            // 执行逻辑操作
            redisHelper.delete(key);
        } else {
            // 设置失败次数计数器, 当到达5次时, 返回失败
            int failCount = 1;
            while(failCount <= 5){
                // 等待100ms重试
                try {
                    Thread.sleep(100l);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if (redisHelper.lock(key)){
                    // 执行逻辑操作
                    redisHelper.delete(key);
                }else{
                    failCount ++;
                }
            }
            throw new RuntimeException("现在创建的人太多了, 请稍等再试");
        }
    }

}
