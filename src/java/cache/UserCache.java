/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cache;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 *
 * @author LinhNguyenDuc
 */
public class UserCache {
    private Cache<String, TempUser> cache;

    public UserCache() {
        this.cache = CacheBuilder.newBuilder()
                .expireAfterWrite(5, TimeUnit.MINUTES) // Thiết lập thời gian hết hạn
                .build();
    }

    public Cache<String, TempUser> getCache() {
        return cache;
    }

    public void setCache(Cache<String, TempUser> cache) {
        this.cache = cache;
    }
}
