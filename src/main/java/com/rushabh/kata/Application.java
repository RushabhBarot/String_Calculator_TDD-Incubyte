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

            // Before passing the input to the calculator, we replace any literal '\n'
            // sequences typed by the user with the actual newline character that the
            // calculator expects. This makes the command-line interface more intuitive.
            String processedInput = input.replace("\\n", "\n");

            if ("exit".equalsIgnoreCase(input)) {
                break;
            }

            try {
                // Pass the PROCESSED input to the calculator.
                int result = calculator.add(processedInput);
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
