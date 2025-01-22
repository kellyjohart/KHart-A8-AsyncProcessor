package com.coderscampus.assignment;

import java.util.Map;

public class AsynchronousApplication {
    public static void main(String[] args) {
        AsynchronousProcessor asynchronousProcessor = new AsynchronousProcessor();
        Map<Integer, Integer> results = asynchronousProcessor.processNumbers();

        // formatting purposes
        for (int i = 1; i <= 10; i++) {
            System.out.print(i + "=" + results.getOrDefault(i, 0));
            if (i < 10) {
                System.out.print(", ");
            }
        }
    }
}
