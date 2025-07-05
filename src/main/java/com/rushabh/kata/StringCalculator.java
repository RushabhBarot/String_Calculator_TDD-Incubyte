package com.rushabh.kata;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class StringCalculator {

    // This constant clearly defines the standard separators we support.
    private static final String DEFAULT_DELIMITERS_REGEX = ",|\n";

    private int addCallCount = 0; // Add a private field to hold state

    public int GetCalledCount() {
        return this.addCallCount;
    }
    /**
     * Takes a string of numbers and returns their sum.
     * @param numbers The input string of numbers.
     * @return The sum as an integer.
     */
    public int add(String numbers) {

        this.addCallCount++; // Increment the counter at the start of every call

        // Add the null check BEFORE we try to call any methods on 'numbers'
        if (numbers == null || numbers.isEmpty()) {
            return 0;
        }

        // The logic for splitting the string is now isolated.
        String[] numberArray = splitNumbers(numbers);

        // Convert string array to a list of integers
        List<Integer> intList = Arrays.stream(numberArray)
                .map(String::trim)
                .map(Integer::parseInt)
                .toList();

        // Find all negative numbers
        List<Integer> negativeNumbers = intList.stream()
                .filter(n -> n < 0)
                .toList();

        // If there are any negative numbers, throw an exception
        if (!negativeNumbers.isEmpty()) {
            throw new IllegalArgumentException("negative numbers not allowed: " +
                    negativeNumbers.stream()
                            .map(String::valueOf)
                            .collect(Collectors.joining(",")));
        }

        // If no negatives, return the sum
        return intList.stream()
                .filter(n -> n <= 1000)
                .mapToInt(Integer::intValue)
                .sum();
    }

    /**
     * This private helper method is responsible ONLY for splitting the input string
     * into an array of number strings, handling both default and custom delimiters.
     * @param numbers The raw input string.
     * @return An array of strings, each representing a number.
     */
    private String[] splitNumbers(String numbers) {
        String numbersPart = numbers;
        String delimiters = DEFAULT_DELIMITERS_REGEX;

        if (numbers.startsWith("//")) {
            int newlineIndex = numbers.indexOf('\n');

            // The delimiter can be found between "//" and the newline.
            String customDelimiter = numbers.substring(2, newlineIndex);
            delimiters = customDelimiter;

            // The actual numbers are after the newline.
            numbersPart = numbers.substring(newlineIndex + 1);
        }

        return numbersPart.split(delimiters);
    }

}
