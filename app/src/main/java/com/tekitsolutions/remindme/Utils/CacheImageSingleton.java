package com.tekitsolutions.remindme.Utils;

import android.graphics.Bitmap;

import java.util.HashMap;

public class CacheImageSingleton {

    private static CacheImageSingleton cacheImageSingleton = new CacheImageSingleton();
    private HashMap<String, Bitmap> categoryHashMap;

    private CacheImageSingleton() {
        categoryHashMap = new HashMap<String, Bitmap>();
    }

    public static CacheImageSingleton getInstance() {
        return cacheImageSingleton;
    }

    public void clearCache() {
        categoryHashMap.clear();
    }

    public void addToCache(String key, Bitmap bitmap) {
        categoryHashMap.put(key, bitmap);
    }

    public boolean isContain(String key) {
        return categoryHashMap.containsKey(key);
    }

    public Bitmap getBitmap(String key) {
        if (isContain(key))
            return categoryHashMap.get(key);
        return null;//Default bitmap
    }

}
