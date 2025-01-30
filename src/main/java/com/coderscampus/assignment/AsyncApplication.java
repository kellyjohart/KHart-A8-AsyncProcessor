package com.coderscampus.assignment;

public class AsyncApplication {
    public static void main(String[] args) {
        NumberFetcherService processingService = new NumberFetcherService();
        processingService.printResults();
    }
}
