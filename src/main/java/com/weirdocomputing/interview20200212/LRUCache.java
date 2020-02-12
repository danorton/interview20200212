package com.weirdocomputing.interview20200212;

// Provide the implementation for this LRU cache class.
// Keys are unique
// Cache size is limited
// MRU is set for both get and put
public interface LRUCache<K, V> {
    V get(K k);
    void put(K k, V v);
}