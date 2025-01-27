package com.coderscampus.assignment;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class AsynchronousProcessor {
    private final Assignment8 assignment8;
    private final Map<Integer, Integer> countNumbers;

    public AsynchronousProcessor() {
        this.assignment8 = new Assignment8();
        this.countNumbers = new ConcurrentHashMap<>();
    }

    public Map<Integer, Integer> processNumbers() {
        ExecutorService executor = Executors.newCachedThreadPool();
        try {
            for (int i = 0; i < 1000; i++) {
                executor.execute(() ->
                        assignment8.getNumbers()
                                .forEach(number -> countNumbers.merge(number, 1, Integer::sum)));
            }

        } finally {
            executor.shutdown();
            //option that prevents shutdown before the task is complete
            try {
                executor.awaitTermination(1, TimeUnit.MINUTES);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        return countNumbers;
    }
}