package cn.gyt.bs.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * 操作Redis的工具类
 *
 * @author Administrator
 */
@Slf4j
@Component
public class RedisUtil {

    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * 设置缓存失效时间
     *
     * @param key  键
     * @param time 时间（秒）
     * @return
     */
    public boolean expire(String key, long time) {
        try {
            if (time > 0) {
                redisTemplate.expire(key, time, TimeUnit.SECONDS);
            }
            return true;
        } catch (Exception e) {
            log.info("设置缓存失效时间失败：({})", e.getMessage());
            return false;
        }
    }

    /**
     * 根据key获取过期时间
     *
     * @param key 键 不能为null
     * @return 时间（秒） 返回0代表永久有效
     */
    public long getExpire(String key) {
        return redisTemplate.getExpire(key, TimeUnit.SECONDS);
    }

    /**
     * 判断key是否存在
     *
     * @param key 键
     * @return true存在 false不存在
     */
    public boolean hasKey(String key) {
        try {
            return redisTemplate.hasKey(key);
        } catch (Exception e) {
            log.info("key值不存在：({})", e.getMessage());
            return false;
        }
    }

    /**
     * 删除缓存
     *
     * @param key 可以传一个值 或多个
     */
    public void delete(String... key) {
        if (key != null && key.length > 0) {
            if (key.length == 1) {
                redisTemplate.delete(key[0]);
            } else {
                redisTemplate.delete(CollectionUtils.arrayToList(key));
            }
        }
    }

    /** =========================== String =====================**/

    /**
     * 普通缓存获取
     *
     * @param key 键
     * @return 值
     */
    public Object get(String key) {
        return key == null ? null : redisTemplate.opsForValue().get(key);
    }

    /**
     * 设置缓存
     *
     * @param key   键
     * @param value 值
     * @return
     */
    public boolean set(String key, Object value) {
        try {
            redisTemplate.opsForValue().set(key, value);
            return true;
        } catch (Exception e) {
            log.info("设置缓存失败：({})", e.getMessage());
            return false;
        }
    }

    /**
     * 设置缓存并设置过期时间
     *
     * @param key   键
     * @param value 值
     * @param time  时间（秒） time要大于0 如果time小于等于0 将设置无限期
     * @return
     */
    public boolean set(String key, Object value, long time) {
        try {
            if (time > 0) {
                redisTemplate.opsForValue().set(key, value, time, TimeUnit.SECONDS);
            } else {
                set(key, value);

            }
            return true;
        } catch (Exception e) {
            log.info("放入缓存失败：({})", e.getMessage());
            return false;
        }
    }

    /** =========================== Map =====================**/

    /**
     * 获取Hash值
     *
     * @param key  键 不能为null
     * @param item 项 不能为null
     * @return 值
     */
    public Object hashGet(String key, String item) {
        return redisTemplate.opsForHash().get(key, item);
    }

    /**
     * 获取hashKey对应的所有键值
     *
     * @param key 键
     * @return 对应的多个键值
     */
    public Map<Object, Object> hashMapGet(String key) {
        return redisTemplate.opsForHash().entries(key);
    }

    /**
     * HashSet
     *
     * @param key 键
     * @param map 值
     * @return
     */
    public boolean hashMapSet(String key, Map<String, Object> map) {
        try {
            redisTemplate.opsForHash().putAll(key, map);
            return true;
        } catch (Exception e) {
            log.info("设置缓存失败：（{}）", e.getMessage());
            return false;
        }
    }

    /**
     * HashSet 并设置时间
     *
     * @param key 键
     * @param map 值
     * @return
     */
    public boolean hashMapSet(String key, Map<String, Object> map, long time) {
        try {
            redisTemplate.opsForHash().putAll(key, map);
            if (time > 0) {
                expire(key, time);
            }
            return true;
        } catch (Exception e) {
            log.info("设置缓存失败：（{}）", e.getMessage());
            return false;
        }
    }

    /**
     * 向一张hash表放入数据，如果不存在则创建
     *
     * @param key   键
     * @param item  项
     * @param value 值
     * @param time  时间（秒）注意：如果已存在的hash表有时间，这里将会替换原有的时间
     * @return
     */
    public boolean hashMapSet(String key, String item, Object value, long time) {
        try {
            redisTemplate.opsForHash().put(key, item, value);
            if (time > 0) {
                expire(key, time);
            }
            return true;
        } catch (Exception e) {
            log.info("设置缓存失败：（{}）", e.getMessage());
            return false;
        }
    }

    /**
     * 删除hash表中的值
     *
     * @param key  键 不能为null
     * @param item 项 可以是多个 不能为null
     */
    public void hashMapDelete(String key, Object... item) {
        redisTemplate.opsForHash().delete(key, item);
    }

    /**
     * 判断hash表中是否有该项的值
     *
     * @param key  键 不能为null
     * @param item 项 不能为null
     * @return
     */
    public boolean HashMapHasKey(String key, String item) {
        return redisTemplate.opsForHash().hasKey(key, item);
    }
}
