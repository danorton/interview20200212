package com.weirdocomputing.interview20200212;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertLinesMatch;

class MyLRUCacheTest {
    private static final int TEST_CACHE_MAX_SIZE = 5;
    private static final String[] TEST_CACHE_KEYS = {"a", "b", "c"};
    private static final String[] GET_TEST_KEYS = {"a", "g", "e", "b", "x"};
    private static final String[][] GET_TEST_RESULTS_EXPECTED = {
            {"b", "c", "a"},
            {"b", "c", "a"},
            {"b", "c", "a"},
            {"c", "a", "b"},
            {"c", "a", "b"},
    };
    private static final String[] PUT_TEST_KEYS = {"x", "y", "z", "a", "d", "i"};
    private static final String[][] PUT_TEST_RESULTS_EXPECTED = {
            {"a", "b", "c", "x"},
            {"a", "b", "c", "x", "y"},
            {"b", "c", "x", "y", "z"},
            {"c", "x", "y", "z", "a"},
            {"x", "y", "z", "a", "d"},
            {"y", "z", "a", "d", "i"}
    };
    private MyLRUCache<String,String> testCache;

    private static void assertResultsAreExpected(String[] expectedKeys, MyLRUCache<String,String> actualResults) {
        String[] expectedValues = expectedKeys.clone();
        Arrays.asList(expectedValues).replaceAll(k -> (k+"_v"));
        assertLinesMatch(Arrays.asList(expectedKeys), actualResults.getKeys());
        assertLinesMatch(Arrays.asList(expectedValues), actualResults.getValues());
    }

    @BeforeEach
    void beforeEach() {
        testCache = new MyLRUCache<String,String>(TEST_CACHE_MAX_SIZE);
        for (String k: TEST_CACHE_KEYS) {
            testCache.put(k, k + "_v");
        }
    }

    @Test
    void get() {
        for (int i = 0; i < GET_TEST_KEYS.length; i++) {
            testCache.get(GET_TEST_KEYS[i]);
            assertResultsAreExpected(GET_TEST_RESULTS_EXPECTED[i], testCache);
        }
    }

    @Test
    void put() {
        for (int i = 0; i < PUT_TEST_KEYS.length; i++) {
            testCache.put(PUT_TEST_KEYS[i], PUT_TEST_KEYS[i] + "_v");
            assertResultsAreExpected(PUT_TEST_RESULTS_EXPECTED[i], testCache);
        }
    }
}