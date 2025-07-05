package com.rushabh.kata;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

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

    @Test
    @DisplayName("Should support a custom delimiter specified at the start of the string")
    void testAdd_shouldSupportCustomDelimiter() {
        // Arrange
        StringCalculator calculator = new StringCalculator();
        String input = "//;\n1;2";
        int expected = 3;

        // Act
        int result = calculator.add(input);

        // Assert
        assertEquals(expected, result);
    }

    @Test
    @DisplayName("Should throw an exception for a single negative number")
    void testAdd_shouldThrowException_forNegativeNumber() {
        // Arrange
        StringCalculator calculator = new StringCalculator();
        String input = "1,-2,3";
        String expectedMessage = "negative numbers not allowed: -2";

        // Act & Assert
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            calculator.add(input);
        });

        assertEquals(expectedMessage, exception.getMessage());
    }

    @Test
    @DisplayName("Should throw an exception showing all negative numbers in the message")
    void testAdd_shouldThrowException_listingAllNegativeNumbers() {
        // Arrange
        StringCalculator calculator = new StringCalculator();
        String input = "1,-2,-5,3";
        String expectedMessage = "negative numbers not allowed: -2,-5";

        // Act & Assert
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            calculator.add(input);
        });

        assertEquals(expectedMessage, exception.getMessage());
    }

    @Test
    @DisplayName("GetCalledCount should return the number of times add() was invoked")
    void testGetCalledCount_shouldReturnTotalInvocations() {
        // Arrange
        StringCalculator calculator = new StringCalculator();

        // Act & Assert
        assertEquals(0, calculator.GetCalledCount(), "Count should be 0 initially.");

        calculator.add("");
        assertEquals(1, calculator.GetCalledCount(), "Count should be 1 after one call.");

        calculator.add("1,2");
        assertEquals(2, calculator.GetCalledCount(), "Count should be 2 after two calls.");

        // Even calls that throw exceptions should be counted
        assertThrows(IllegalArgumentException.class, () -> calculator.add("-1"));
        assertEquals(3, calculator.GetCalledCount(), "Count should be 3 after a call that throws.");
    }
}
