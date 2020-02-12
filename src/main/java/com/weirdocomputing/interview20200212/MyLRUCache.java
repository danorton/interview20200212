package com.weirdocomputing.interview20200212;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * Generic class for LRU implementation
 * © 2020 Daniel Norton
 * @param <K> key
 * @param <V> value
 */
public class MyLRUCache<K, V> implements LRUCache<K, V>  {
    private final LinkedHashMap<K, V> hashMap;
    private final int capacity;

    MyLRUCache(int capacity) {
        this.capacity = capacity;
        hashMap = new LinkedHashMap<>(capacity, (float) 0.75, true);
    }

    @Override
    public V get(K k) {
        return hashMap.get(k);
    }

    @Override
    public void put(@NotNull K k, @Nullable V v) {
        if ((!hashMap.containsKey(k)) && hashMap.size() >= capacity) {
            // remove LRU if new key and cache is full
            hashMap.remove(hashMap.entrySet().iterator().next().getKey());
        }
        hashMap.put(k, v);
    }

    List<K> getKeys() {
        return new ArrayList<>(hashMap.keySet());
    }

    List<V> getValues() {
        return new ArrayList<>(hashMap.values());
    }

}
