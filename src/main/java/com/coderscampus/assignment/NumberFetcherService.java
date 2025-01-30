package com.coderscampus.assignment;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

public class NumberFetcherService {
    private final Assignment8 assignment8;
    private final Map<Integer, Integer> countNumbers;
    private final ExecutorService executorService;

    public NumberFetcherService() {
        this.assignment8 = new Assignment8();
        this.countNumbers = new ConcurrentHashMap<>();
        this.executorService = Executors.newCachedThreadPool();
    }

    public Map<Integer, Integer> processNumbers() {
        List<CompletableFuture<Void>> futures = new ArrayList<>();

        for (int i = 0; i < 1000; i++) {
            CompletableFuture<Void> future = CompletableFuture
                                                            .runAsync(() -> assignment8
                                                            .getNumbers()
                                                            .forEach(number -> countNumbers
                                                            .merge(number, 1, Integer::sum)), executorService);futures
                                                            .add(future);
        }
        CompletableFuture.allOf(futures.toArray(new CompletableFuture[0])).join();

        executorService.shutdown();
        return countNumbers;
    }

    public void printResults() {
        Map<Integer, Integer> results = processNumbers();
        System.out.println("\nNumber counts:");
        System.out.println(results.entrySet()
                                            .stream()
                                            .sorted(Map.Entry.comparingByKey())
                                            .map(entry -> entry.getKey() + "=" + entry.getValue())
                                            .collect(Collectors.joining(", ")));
    }
}
