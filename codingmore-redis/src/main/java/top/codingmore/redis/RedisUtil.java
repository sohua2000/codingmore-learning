package top.codingmore.redis;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.Collections;
import java.util.concurrent.TimeUnit;
import java.util.List;

/**
 * redis 工具类
 * @Author ZhangYi
 */
@Component
@Slf4j
public class RedisUtil {

    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    /**
     * lua 脚本
     */
    public static final String SETNX_SCRIPT = "return redis.call('setnx',KEYS[1], ARGV[1])";

    /**
     * redis实现分布式锁
     * @param key
     * @return
     */
    public boolean setNx(String key,int time) {
        //自定义脚本
        DefaultRedisScript<List> script = new DefaultRedisScript<>(SETNX_SCRIPT, List.class);
        //执行脚本,传入参数,由于value没啥用,这里随便写死的"1"
        List<Long> rst = redisTemplate.execute(script, Collections.singletonList(key), "1");
        //返回1,表示设置成功,拿到锁
        if(rst.get(0) == 1){
            log.info(key+"成功拿到锁");
            //设置过期时间
            expire(key,time);
            log.info(key+"已成功设置过期时间:"+time +" 秒");
            return true;
        }else{
            long expire = getExpire(key);
            log.info(key+"未拿到到锁,还有"+expire+"释放");
            return false;
        }
    }

    /**
     * 指定缓存失效时间
     *
     * @param key  键
     * @param time 时间(秒)
     * @return
     */
    public void expire(String key, long time) {
        redisTemplate.expire(key, time, TimeUnit.SECONDS);
    }

    /**
     * 根据key 获取过期时间
     *
     * @param key 键 不能为null
     * @return 时间(秒) 返回0代表为永久有效
     */
    public long getExpire(String key) {
        return redisTemplate.getExpire(key, TimeUnit.SECONDS);
    }

    /**
     * 删除缓存
     *
     * @param key 可以传一个值 或多个
     */
    @SuppressWarnings("unchecked")
    public void del(String... key) {
        if (key != null && key.length > 0) {
            if (key.length == 1) {
                redisTemplate.delete(key[0]);
            } else {
                redisTemplate.delete((Collection<String>) CollectionUtils.arrayToList(key));
            }
        }
    }


}
