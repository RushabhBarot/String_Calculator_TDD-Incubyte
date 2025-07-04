package com.rushabh.kata;

public class StringCalculator {

    /**
     * Takes a string of numbers and returns their sum.
     * @param numbers The input string of numbers.
     * @return The sum as an integer.
     */

    public int add(String numbers) {
        // Add the null check BEFORE we try to call any methods on 'numbers'
        if (numbers == null || numbers.isEmpty()) {
            return 0;
        }
        // Otherwise, we assume it's a single number.
        return Integer.parseInt(numbers);
    }

}
