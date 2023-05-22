package top.codingmore.codingmorehelloword.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import top.codingmore.codingmorehelloword.redis.CommonRedisHelper;

import javax.annotation.Resource;

/**
 * 微信搜索「沉默王二」，回复 Java
 *
 * @author 沉默王二
 * @date 5/17/22
 */
@Controller
@Slf4j
public class HelloController {

//    @Resource
//    RedisTemplate<String, Object> redisTemplate;
    @Resource
    CommonRedisHelper commonRedisHelper;

    @GetMapping("/hello")
    @ResponseBody
    public String hello() {

        log.debug("entry hello...");


        return "hello, springboot，沉默王二是傻 X";
    }

    @GetMapping("/hello2")
    @ResponseBody
    public String hello2() {

        log.debug("entry hello2...");
//        // 添加
//        redisTemplate.opsForValue().set("name1","沉默王二hello2");
//        // 查询
//        System.out.println(redisTemplate.opsForValue().get("name1"));

        hello2test();
        return "hello, springboot，沉默王二是傻 XXXhello2";
    }

    private void hello2test() {

        log.debug("entry hello2test...-----------------------------");
        String  key = "lockkey";
//        CommonRedisHelper redisHelper = new CommonRedisHelper();
        CommonRedisHelper redisHelper = commonRedisHelper;
        boolean lock = redisHelper.lock(key);
        if (lock) {
            // 执行逻辑操作
            System.out.println("执行逻辑操作-----1");

            redisHelper.delete(key);
        } else {
            // 设置失败次数计数器, 当到达5次时, 返回失败
            System.out.println("设置失败-----1");

            int failCount = 1;
            while(failCount <= 5){
                // 等待100ms重试
                System.out.println("尝试重试-----"+failCount);
                try {
                    Thread.sleep(100l);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if (redisHelper.lock(key)){
                    // 执行逻辑操作
                    System.out.println("执行逻辑操作-----2");

                    redisHelper.delete(key);
                }else{
                    failCount ++;
                }
            }
            throw new RuntimeException("现在创建的人太多了, 请稍等再试");

        }
    }
}
