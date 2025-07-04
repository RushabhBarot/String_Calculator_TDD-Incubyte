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
}
