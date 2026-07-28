package com.tdd;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

public class AAAPatternTest {

    private Calculator calculator;

    // @BeforeEach = runs before EVERY test method
    // Like @Before in JUnit 4
    @BeforeEach
    public void setUp() {
        System.out.println("Setting up test...");
        calculator = new Calculator(); // fresh instance for each test
    }

    // @AfterEach = runs after EVERY test method
    // Like @After in JUnit 4
    @AfterEach
    public void tearDown() {
        System.out.println("Tearing down test...");
        calculator = null; // cleanup
    }

    // @BeforeAll = runs ONCE before all tests
    @BeforeAll
    public static void setUpAll() {
        System.out.println("=== Starting Calculator Tests ===");
    }

    // @AfterAll = runs ONCE after all tests
    @AfterAll
    public static void tearDownAll() {
        System.out.println("=== All Calculator Tests Complete ===");
    }

    @Test
    public void testAddition_AAA() {
        // ARRANGE - prepare test data
        int firstNumber = 10;
        int secondNumber = 5;
        int expectedResult = 15;

        // ACT - perform the action
        int actualResult = calculator.add(firstNumber, secondNumber);

        // ASSERT - verify the result
        assertEquals(expectedResult, actualResult,
                "Addition of 10 + 5 should be 15");
        System.out.println("Addition test passed!");
    }

    @Test
    public void testSubtraction_AAA() {
        // ARRANGE
        int firstNumber = 10;
        int secondNumber = 5;
        int expectedResult = 5;

        // ACT
        int actualResult = calculator.subtract(firstNumber, secondNumber);

        // ASSERT
        assertEquals(expectedResult, actualResult,
                "Subtraction of 10 - 5 should be 5");
        System.out.println("Subtraction test passed!");
    }

    @Test
    public void testMultiplication_AAA() {
        // ARRANGE
        int firstNumber = 10;
        int secondNumber = 5;
        int expectedResult = 50;

        // ACT
        int actualResult = calculator.multiply(firstNumber, secondNumber);

        // ASSERT
        assertEquals(expectedResult, actualResult,
                "Multiplication of 10 * 5 should be 50");
        System.out.println("Multiplication test passed!");
    }

    @Test
    public void testDivision_AAA() {
        // ARRANGE
        int firstNumber = 10;
        int secondNumber = 2;
        double expectedResult = 5.0;

        // ACT
        double actualResult = calculator.divide(firstNumber, secondNumber);

        // ASSERT
        assertEquals(expectedResult, actualResult,
                "Division of 10 / 2 should be 5.0");
        System.out.println("Division test passed!");
    }

    @Test
    public void testDivideByZero_AAA() {
        // ARRANGE
        int firstNumber = 10;
        int secondNumber = 0;

        // ACT & ASSERT
        assertThrows(ArithmeticException.class,
                () -> calculator.divide(firstNumber, secondNumber),
                "Should throw exception for divide by zero");
        System.out.println("Divide by zero test passed!");
    }
}
