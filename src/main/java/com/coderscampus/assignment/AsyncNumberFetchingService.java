package com.coderscampus.assignment;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

public class AsyncNumberFetchingService {
    private final Assignment8 assignment8;
    private final Map<Integer, Integer> countNumbers;
    private final ExecutorService executorService;

    public AsyncNumberFetchingService() {
        this.assignment8 = new Assignment8();
        this.countNumbers = new ConcurrentHashMap<>();
        this.executorService = Executors.newCachedThreadPool();
    }

    public void processNumbers() {
        List<CompletableFuture<Void>> futures = new ArrayList<>();

        for (int i = 0; i < 1000; i++) {
            CompletableFuture<Void> future = CompletableFuture.runAsync(() -> assignment8.getNumbers()
                    .forEach(number -> countNumbers.merge(number, 1, Integer::sum)), executorService);
                    futures.add(future);
        }
        CompletableFuture.allOf(futures.toArray(new CompletableFuture[0])).join();


    }

    public void printResults() {
        System.out.println("\nNumber counts:");
        System.out.println(countNumbers.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByKey())
                .map(entry -> entry.getKey() + "=" + entry.getValue())
                .collect(Collectors.joining(", ")));
    }

    public void shutdown() {
        executorService.shutdown();
    }
}
