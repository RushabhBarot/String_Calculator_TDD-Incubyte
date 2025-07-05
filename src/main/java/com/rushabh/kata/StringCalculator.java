package com.rushabh.kata;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
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
     * It acts as a coordinator, delegating the complex parsing to another helper.
     * @param numbers The raw input string.
     * @return An array of strings, each representing a number.
     */
    private String[] splitNumbers(String numbers) {

        if (!numbers.startsWith("//")) {
            // Handle the default case simply
            return numbers.split(DEFAULT_DELIMITERS_REGEX);
        }


            int newlineIndex = numbers.indexOf('\n');
            String delimiterDefinition = numbers.substring(2, newlineIndex);
            // The actual numbers are after the newline.
            String numbersPart = numbers.substring(newlineIndex + 1);

        // Delegate the complex parsing to a helper method
        String delimiterRegex = parseDelimiterRegex(delimiterDefinition);

        return numbersPart.split(delimiterRegex);
    }

    /**
     * Parses the delimiter definition string and returns a single, combined
     * regex string suitable for the String.split() method.
     * This version uses basic string manipulation instead of regular expressions.
     *
     * @param delimiterDefinition The part of the input string that defines the delimiters.
     * @return A regex string for splitting.
     */
    private String parseDelimiterRegex(String delimiterDefinition) {
        // If the definition doesn't start with a bracket, assume it's the simple format (e.g., ";").
        if (!delimiterDefinition.startsWith("[")) {
            return Pattern.quote(delimiterDefinition);
        }

        List<String> delimiterList = new ArrayList<>();
        int searchStartIndex = 0;

        // Loop through the string to find all bracket pairs.
        while (searchStartIndex < delimiterDefinition.length()) {
            // Find the next opening bracket from our starting position.
            int startBracketIndex = delimiterDefinition.indexOf('[', searchStartIndex);
            if (startBracketIndex == -1) {
                // No more opening brackets found, we're done.
                break;
            }

            // Find the closing bracket that comes after the opening one.
            int endBracketIndex = delimiterDefinition.indexOf(']', startBracketIndex);
            if (endBracketIndex == -1) {
                // Found an opening bracket without a closing one, this is an invalid format.
                // We can break or throw an error, but for now, we'll just stop.
                break;
            }

            // Extract the delimiter, which is the text between the brackets.
            String delimiter = delimiterDefinition.substring(startBracketIndex + 1, endBracketIndex);
            delimiterList.add(delimiter);

            // Move our search position to just after the closing bracket we just processed.
            searchStartIndex = endBracketIndex + 1;
        }

        // BUILD THE FINAL REGEX FOR SPLITTING
        // If we found bracketed delimiters, we'll join them with a regex "OR" operator, which is '|'.
        // We use Pattern.quote() on each one to ensure they are treated as literal strings,
        // preventing characters like '*' or '+' from being interpreted as regex commands.
        // Example output for "[**][%%]": "\Q**\E|\Q%%\E"
        return delimiterList.stream()
                .map(Pattern::quote)
                .collect(Collectors.joining("|"));
    }

}
