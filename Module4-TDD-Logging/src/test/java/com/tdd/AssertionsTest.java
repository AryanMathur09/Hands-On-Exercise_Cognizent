package com.tdd;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class AssertionsTest {

    @Test
    public void testBasicAssertions() {
        System.out.println("Running basic assertions test...");

        // assertEquals - checks if two values are equal
        assertEquals(5, 2 + 3, "2 + 3 should equal 5");

        // assertTrue - checks if condition is true
        assertTrue(5 > 3, "5 should be greater than 3");

        // assertFalse - checks if condition is false
        assertFalse(5 < 3, "5 should not be less than 3");

        // assertNull - checks if value is null
        assertNull(null, "Value should be null");

        // assertNotNull - checks if value is not null
        assertNotNull(new Object(), "Object should not be null");

        System.out.println("All basic assertions passed!");
    }

    @Test
    public void testCalculatorAssertions() {
        Calculator calculator = new Calculator();

        // Test addition
        assertEquals(10, calculator.add(7, 3), "7 + 3 should be 10");

        // Test subtraction
        assertEquals(4, calculator.subtract(7, 3), "7 - 3 should be 4");

        // Test multiplication
        assertEquals(21, calculator.multiply(7, 3), "7 * 3 should be 21");

        // Test division
        assertEquals(2.5, calculator.divide(5, 2), "5 / 2 should be 2.5");

        System.out.println("All calculator assertions passed!");
    }

    @Test
    public void testExceptionAssertion() {
        Calculator calculator = new Calculator();

        // assertThrows - checks if exception is thrown
        ArithmeticException exception = assertThrows(
                ArithmeticException.class,
                () -> calculator.divide(10, 0),
                "Should throw ArithmeticException for divide by zero"
        );

        assertEquals("Cannot divide by zero!", exception.getMessage());
        System.out.println("Exception assertion passed!");
    }

    @Test
    public void testStringAssertions() {
        String name = "Cognizant";

        // Various string assertions
        assertEquals("Cognizant", name);
        assertTrue(name.startsWith("Cog"));
        assertTrue(name.endsWith("ant"));
        assertTrue(name.contains("nizant"));
        assertFalse(name.isEmpty());

        System.out.println("String assertions passed!");
    }
}