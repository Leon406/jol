package org.openjdk;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author suchu
 * @date 2020/8/7
 */
public class Stats {
    static Map<String, AtomicInteger> failedCount = new HashMap<>();
    public Map<String, AtomicInteger> failedCount2 = new HashMap<>(16);

    public static int incr(String key) {
        AtomicInteger integer = failedCount.computeIfAbsent(key, (k) -> new AtomicInteger(0));
        integer.getAndIncrement();
        return integer.get();
    }

    public static void remove(String key) {
        failedCount.remove(key);
    }

    public void say() {
        System.out.println("hello");
    }

    public void say(String word) {
        System.out.println(word);
    }
}
