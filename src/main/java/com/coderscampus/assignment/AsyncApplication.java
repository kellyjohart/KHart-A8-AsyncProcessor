package com.coderscampus.assignment;

public class AsyncApplication {
    public static void main(String[] args) {
        AsyncNumberFetchingService numberFetchingService = new AsyncNumberFetchingService();
        numberFetchingService.processNumbers();
        numberFetchingService.printResults();
        numberFetchingService.shutdown();
    }
}
