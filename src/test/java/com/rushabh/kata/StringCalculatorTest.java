package com.rushabh.kata;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class StringCalculatorTest {

    private StringCalculator calculator;

    // This method runs before each test, ensuring a fresh calculator instance.
    @BeforeEach
    void setUp() {
        calculator = new StringCalculator();
    }

    @Test
    @DisplayName("Should return 0 for an empty string")
    void testAdd_shouldReturnZero_forEmptyString() {
        // Arrange
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

    @Test
    @DisplayName("Numbers bigger than 1000 should be ignored")
    void testAdd_shouldIgnoreNumbersGreaterThan1000() {
        //Act & Arrange
        assertEquals(2, calculator.add("1001,2"), "1001 should be ignored");
        assertEquals(1002, calculator.add("1000,2"), "1000 should not be ignored");
    }

    @Test
    @DisplayName("Should support delimiters of any length defined in brackets")
    void testAdd_shouldSupportAnyLengthDelimiter() {
       // Arrange
        String input = "//[***]\n1***2***3";
        int expected = 6;

        // Act
        int result = calculator.add(input);

        // Assert
        assertEquals(expected, result);
    }

    @Test
    @DisplayName("Should support multiple delimiters of any length")
    void testAdd_shouldSupportMultipleDelimiters() {
        // Arrange
        String input = "//[*][%]\n1*2%3";
        int expected = 6;

        // Act
        int result = calculator.add(input);

        // Assert
        assertEquals(expected, result);

        // Also test the "any length" part of this requirement
        //Arrange
        input = "//[**][%%]\n1**2%%3";

        // Act
        result = calculator.add(input);

        //Assert
        assertEquals(expected, result);
    }
}
