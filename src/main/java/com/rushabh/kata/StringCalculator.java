package com.rushabh.kata;

public class StringCalculator {

    /**
     * Takes a string of numbers and returns their sum.
     * @param numbers The input string of numbers.
     * @return The sum as an integer.
     */

    public int add(String numbers) {
        // If the input string is empty, we follow the first rule.
        if (numbers.isEmpty()) {
            return 0;
        }
        // Otherwise, we assume it's a single number.
        return Integer.parseInt(numbers);
    }

}
