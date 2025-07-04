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

        // Split the string by the comma delimiter
        String[] numberArray = numbers.split(",");
        int sum = 0;
        for (String numStr : numberArray) {
            // Convert each part to an integer and add to the sum
            sum += Integer.parseInt(numStr.trim());
        }
        return sum;
    }

}
