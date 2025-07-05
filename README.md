# String Calculator TDD Kata

This project is a solution to the String Calculator TDD Kata, implemented in Java using Maven and JUnit 5. The primary goal was to follow a strict Test-Driven Development (TDD) process, with frequent, atomic commits that clearly show the Red-Green-Refactor cycle.

## Features Implemented
- Handles an empty string, returning 0.
- Handles single and multiple numbers separated by commas.
- Handles newlines as an alternative delimiter.
- Supports custom delimiters defined at the beginning of the input string (e.g., `//;\n1;2`).
- Throws an `IllegalArgumentException` for negative numbers, listing all negatives in the exception message.

## How to Run the Project

### Prerequisites
- Java JDK 11 or higher
- Apache Maven

### Running Tests
You can run the tests using the Maven wrapper from the command line:

```bash
.\mvnw.cmd test