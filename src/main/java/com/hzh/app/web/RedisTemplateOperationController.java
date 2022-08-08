package com.hzh.app.web;

import com.hzh.app.canal.UserTmp;
import com.hzh.app.mapper.HzhMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;


@RestController
public class RedisTemplateOperationController {

    //
    // redis淘汰策略，lru lfu
    //


    @Autowired
    private RedisTemplate<String, String> stringStringRedisTemplate;
    @Autowired
    private RedisTemplate<String, Object> redisTemplate;
    @Resource
    private HzhMapper hzhMapper;

    //fixme ===================string===================
    @GetMapping("/get")
    public String get(String key) {

        String value = stringStringRedisTemplate.opsForValue().get(key);
        if (null == value) {
            return null;
        }
        String result = value.toString();

        System.out.println("result= " + result);
        return result;
    }

    @GetMapping("/set")
    public void set(String key) {
        stringStringRedisTemplate.opsForValue().set(key, key);
    }

    @GetMapping("/setexpire")
    public void setexpire(String key) {
        stringStringRedisTemplate.opsForValue().set(key, key, 5, TimeUnit.SECONDS);
    }

    //fixme ===================map===================
    @GetMapping("/allUserTmpAdd2RedisMap")
    public void allUserTmpAdd2RedisMap() {

        List<UserTmp> userTmps = hzhMapper.allUserTmp();
        String key = "userTmps";

        userTmps.forEach(ele -> {
            redisTemplate.opsForHash().put(key, ele.getId(), ele);

        });

        //redisTemplate.expire(key, 10, TimeUnit.SECONDS);
    }

    @GetMapping("/getUserTmpFromRedisMap")
    public Object getUserTmpFromRedisMap(String hashKey) {

        String key = "userTmps";
        Object o = redisTemplate.opsForHash().get(key, hashKey);

        return o;
    }

    //fixme ===================list===================
    @GetMapping("/allUserTmpAdd2RedisList")
    public void allUserTmpAdd2RedisList() {

        List<UserTmp> userTmpList = hzhMapper.allUserTmp();
        String key = "userTmpList";

        userTmpList.forEach(ele -> {
            redisTemplate.opsForList().leftPush(key, ele);

        });
    }

    @GetMapping("/getUserTmpFromRedisListAll")
    public Object getUserTmpFromRedisListAll() {
        String key = "userTmpList";

        Long size = redisTemplate.opsForList().size(key);
        System.out.println("userTmpList size= " + size);
        Object o = redisTemplate.opsForList().range(key, 0, 100);

        return o;
    }

    @GetMapping("/getUserTmpFromRedisList")
    public Object getUserTmpFromRedisList(String key) {
        key = "userTmpList";

        Object o = redisTemplate.opsForList().leftPop(key);

        return o;
    }

    //fixme ===================set===================
    @GetMapping("/allUserTmpAdd2RedisSet")
    public void allUserTmpAdd2RedisSet() {

        List<UserTmp> userTmpList = hzhMapper.allUserTmp();
        String key = "userTmpSet";

        userTmpList.forEach(ele -> {
            redisTemplate.opsForSet().add(key, ele);
        });
    }

    @GetMapping("/allUserTmpAdd2RedisSetOther")
    public void allUserTmpAdd2RedisSetOther() {

        List<UserTmp> userTmpList = hzhMapper.allUserTmp();
        String key = "userTmpSetOther";

        AtomicInteger counter = new AtomicInteger(0);
        userTmpList.forEach(ele -> {
            if (counter.get() < 10) {

                redisTemplate.opsForSet().add(key, ele);
            }
            counter.getAndIncrement();
        });
    }

    @GetMapping("/getUserTmpFromRedisSet")
    public Set<Object> getUserTmpFromRedisSet() {

        String key = "userTmpSet";

        Set<Object> members = redisTemplate.opsForSet().members(key);
        return members;
    }

    @GetMapping("/getUserTmpFromRedisSetDifference")
    public Set<Object> getUserTmpFromRedisSetDifference() {

        String key = "userTmpSet";
        String otherKey = "userTmpSetOther";

        Set<Object> differences = redisTemplate.opsForSet().difference(key, otherKey);
        return differences;
    }

    //fixme ===================zset===================
    @GetMapping("/allUserTmpAdd2RedisZSet")
    public void allUserTmpAdd2RedisZSet() {

        List<UserTmp> userTmpList = hzhMapper.allUserTmp();
        String key = "userTmpZSet";

        userTmpList.forEach(ele -> {
            redisTemplate.opsForZSet().add(key, ele, Double.valueOf(ele.getId()));
        });
    }

    @GetMapping("/getUserTmpFromRedisZSetAll")
    public Set<Object> getUserTmpFromRedisZSetAll() {

        String key = "userTmpZSet";

        Set<Object> range = redisTemplate.opsForZSet().range(key, 0, 100);

        return range;
    }

}
