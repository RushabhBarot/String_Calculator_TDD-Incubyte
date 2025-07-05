package com.rushabh.kata;

import java.util.Scanner;

public class Application {
    public static void main(String[] args) {
        // Create an instance of our calculator library class.
        StringCalculator calculator = new StringCalculator();

        Scanner scanner = new Scanner(System.in);

        System.out.println("String Calculator");
        System.out.println("Enter numbers separated by commas or custom delimiters.");
        System.out.println("Type 'exit' to quit.");
        System.out.println("----------------------------------------------------");

        while (true) {
            System.out.print("> ");
            String input = scanner.nextLine();

            if ("exit".equalsIgnoreCase(input)) {
                break;
            }

            try {
                // Use the calculator to do the work.
                int result = calculator.add(input);
                System.out.println("The sum is: " + result);
            } catch (IllegalArgumentException e) {
                // Gracefully handle exceptions from the calculator.
                System.err.println("Error: " + e.getMessage());
            }
        }

        System.out.println("Exiting calculator. Goodbye!");
        scanner.close();
    }
}
