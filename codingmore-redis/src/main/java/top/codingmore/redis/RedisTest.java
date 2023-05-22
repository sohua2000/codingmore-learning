package top.codingmore.redis;


public class RedisTest {


    public static void test() {

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
