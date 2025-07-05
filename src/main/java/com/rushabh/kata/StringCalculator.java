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

        // Split the string by the comma delimiter or newline
        String[] numberArray = numbers.split(DEFAULT_DELIMITERS_REGEX);
        int sum = 0;
        for (String numStr : numberArray) {
            // Convert each part to an integer and add to the sum
            sum += Integer.parseInt(numStr.trim());
        }
        return sum;
    }

}
