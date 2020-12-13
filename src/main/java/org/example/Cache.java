package org.example;

import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 问题：构建一个本地缓存，缓存的对象是用户ID以及部分用户的信息，在从缓存中获取数据时，如果发现缓存上次更新时间超过30分钟，则需要自动更新一次缓存
 *
 * 实现思路：内存缓存（ConcurrentHashMap实现），超时校验（保存put时间），自动更新（所以要保存获取缓存的动作）
 *
 * 整体还是使用 ConcurrentHashMap 做缓存，缓存的 Key 是 用户ID，缓存的Value 是 一个自定义的类（其中有获取缓存内容的回调方法）
 *
 * 每次 get 时候 先取出 map 内的值
 * 如果值不为空，判断是否超时，没超时返回上次获取的值；
 * 如果超时了，则需要重新计算缓存的Value（回调Action），然后返回最新计算出来的Value；
 *
 */
public class Cache {

    private ConcurrentHashMap<String,CacheValue> cacheMap = new ConcurrentHashMap<>();

    private static class CacheValue{
         final String content;
         final Callable<String> getContentAction;
         final long gmtTime;

        public CacheValue(String content, Callable<String> getContentAction, long gmtTime) {
            this.content = content;
            this.getContentAction = getContentAction;
            this.gmtTime = gmtTime;
        }
    }

    public String put(String key , Callable<String> action){
        try {
            String value = action.call();
            CacheValue cacheValue = new CacheValue(value,action,System.currentTimeMillis());
            cacheMap.put(key,cacheValue);
            return value;
        }catch (Exception e){
            throw new RuntimeException("put cache error");
        }
    }

    public String get(String key){
        CacheValue cacheValue = cacheMap.get(key);
        if(cacheValue == null){
            return null;
        }
        if(isTimeOut(cacheValue.gmtTime)){
            return put(key,cacheValue.getContentAction);
        }
        return cacheValue.content;
    }

    /**
     * 判断当前Value 是否超时
     * @param gmtTime
     * @return
     */
    private static boolean isTimeOut(long gmtTime){
        long current = System.currentTimeMillis();
        // 如果 当前时间与gmt间隔大于 30分钟，返回false
        if((current - gmtTime) >= 30 * 60 * 1000){
            return true;
        }
        return false;
    }

    public static void main(String[] args) {
        Cache cache = new Cache();
        AtomicInteger index = new AtomicInteger();
        cache.put("1",() -> {
            return "yes" + index.getAndIncrement();
        });

        System.out.println(cache.get("1"));
        System.out.println(cache.get("1"));
        System.out.println(cache.get("1"));
        System.out.println(cache.get("1"));

    }

}
