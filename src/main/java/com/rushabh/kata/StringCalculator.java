package com.rushabh.kata;

public class StringCalculator {

    /**
     * Takes a string of numbers and returns their sum.
     * @param numbers The input string of numbers.
     * @return The sum as an integer.
     */

    // This constant clearly defines the standard separators we support.
    private static final String DEFAULT_DELIMITERS_REGEX = ",|\n";

    public int add(String numbers) {

        // Add the null check BEFORE we try to call any methods on 'numbers'
        if (numbers == null || numbers.isEmpty()) {
            return 0;
        }

        // The logic for splitting the string is now isolated.
        String[] numberArray = splitNumbers(numbers);

        int sum = 0;
        for (String numStr : numberArray) {
            // Convert each part to an integer and add to the sum
            sum += Integer.parseInt(numStr.trim());
        }
        return sum;
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
