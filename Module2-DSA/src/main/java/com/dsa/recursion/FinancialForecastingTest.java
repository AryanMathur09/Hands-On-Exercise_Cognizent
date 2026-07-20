package com.dsa.recursion;

public class FinancialForecastingTest {

    public static void main(String[] args) {

        FinancialForcasting forecasting = new FinancialForcasting();

        double presentValue = 10000.0;  // Initial investment
        double growthRate = 0.08;        // 8% annual growth
        int years = 5;

        System.out.println("========================================");
        System.out.println("     FINANCIAL FORECASTING TOOL");
        System.out.println("========================================");
        System.out.println("Present Value : Rs." + presentValue);
        System.out.println("Growth Rate   : " + (growthRate * 100) + "%");
        System.out.println("Years         : " + years);
        System.out.println();

        // ==========================================
        // RECURSIVE APPROACH
        // ==========================================
        System.out.println("--- Recursive Approach ---");
        double recursiveResult = forecasting.calculateFutureValue(
                presentValue, growthRate, years);
        System.out.printf("Future Value (Recursive): Rs.%.2f%n",
                recursiveResult);

        System.out.println();

        // ==========================================
        // ITERATIVE APPROACH
        // ==========================================
        System.out.println("--- Iterative Approach ---");
        double iterativeResult = forecasting.calculateFutureValueIterative(
                presentValue, growthRate, years);
        System.out.printf("Future Value (Iterative): Rs.%.2f%n",
                iterativeResult);

        System.out.println();

        // ==========================================
        // YEAR BY YEAR BREAKDOWN
        // ==========================================
        System.out.println("--- Year by Year Breakdown ---");
        for (int i = 1; i <= years; i++) {
            double value = forecasting.calculateFutureValue(
                    presentValue, growthRate, i);
            System.out.printf("Year %d: Rs.%.2f%n", i, value);
        }

        System.out.println();
        System.out.println("--- Complexity Analysis ---");
        System.out.println("Recursive Approach:");
        System.out.println("  Time Complexity : O(n) - n recursive calls");
        System.out.println("  Space Complexity: O(n) - n frames on call stack");
        System.out.println("  Risk: StackOverflow for very large n!");
        System.out.println();
        System.out.println("Iterative Approach:");
        System.out.println("  Time Complexity : O(n)");
        System.out.println("  Space Complexity: O(1) - no call stack used");
        System.out.println("  Better for large inputs!");
        System.out.println();
        System.out.println("Optimization: Memoization");
        System.out.println("  Stores calculated values");
        System.out.println("  Avoids recalculation");
        System.out.println("  Useful when same year calculated multiple times");
    }
}