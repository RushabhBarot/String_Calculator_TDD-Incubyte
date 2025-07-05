package com.rushabh.kata;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class StringCalculatorTest {

    @Test
    @DisplayName("Should return 0 for an empty string")
    void testAdd_shouldReturnZero_forEmptyString() {
        // Arrange
        StringCalculator calculator = new StringCalculator();
        String input = "";
        int expected = 0;

        // Act
        int result = calculator.add(input);

        // Assert
        assertEquals(expected, result);
    }

    @Test
    @DisplayName("Should return the number itself for a single number string")
    void testAdd_shouldReturnNumber_forSingleNumber() {
        // Arrange
        StringCalculator calculator = new StringCalculator();
        String input = "1";
        int expected = 1;

        // Act
        int result = calculator.add(input);

        // Assert
        assertEquals(expected, result);
    }

    @Test
    @DisplayName("Should return 0 for a null string")
    void testAdd_shouldReturnZero_forNullInput() {
        // Arrange
        StringCalculator calculator = new StringCalculator();
        String input = null;
        int expected = 0;

        // Act
        int result = calculator.add(input);

        // Assert
        assertEquals(expected, result);
    }

    @Test
    @DisplayName("Should return the sum of two comma-separated numbers")
    void testAdd_shouldReturnSum_forTwoNumbers() {
        // Arrange
        StringCalculator calculator = new StringCalculator();
        String input = "1,5";
        int expected = 6;

        // Act
        int result = calculator.add(input);

        // Assert
        assertEquals(expected, result);
    }

    @Test
    @DisplayName("Should return the sum of any amount of comma-separated numbers")
    void testAdd_shouldReturnSum_forAnyAmountOfNumbers() {
        // Arrange
        StringCalculator calculator = new StringCalculator();
        String input = "1,2,3,4,5";
        int expected = 15;

        // Act
        int result = calculator.add(input);

        // Assert
        assertEquals(expected, result);
    }

    @Test
    @DisplayName("Should handle new lines as a delimiter between numbers")
    void testAdd_shouldHandleNewlines_asDelimiter() {
        // Arrange
        StringCalculator calculator = new StringCalculator();
        String input = "1\n2,3";
        int expected = 6;

        // Act
        int result = calculator.add(input);

        // Assert
        assertEquals(expected, result);
    }
}
