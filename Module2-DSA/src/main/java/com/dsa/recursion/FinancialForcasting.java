package com.dsa.recursion;

public class FinancialForcasting {

    // ==========================================
    // RECURSIVE APPROACH
    // Formula: futureValue = presentValue × (1 + growthRate)^years
    // ==========================================

    /**
     * Recursive method to calculate future value
     * Base Case: years == 0 → return present value
     * Recursive Case: multiply by (1 + growthRate) and reduce years by 1
     */
    public double calculateFutureValue(double presentValue,
                                       double growthRate,
                                       int years) {
        // BASE CASE - stopping condition!
        if (years == 0) {
            return presentValue;
        }

        // RECURSIVE CASE - calls itself with reduced years
        return calculateFutureValue(
                presentValue * (1 + growthRate),
                growthRate,
                years - 1  // reducing problem size each time!
        );
    }

    // ==========================================
    // OPTIMIZED: ITERATIVE APPROACH
    // Same result but no stack overflow risk
    // ==========================================
    public double calculateFutureValueIterative(double presentValue,
                                                double growthRate,
                                                int years) {
        double result = presentValue;
        for (int i = 0; i < years; i++) {
            result = result * (1 + growthRate);
        }
        return result;
    }

    // ==========================================
    // MEMOIZATION - Optimized Recursion
    // Stores already calculated values
    // Avoids recalculation
    // ==========================================
    private double[] memo;

    public double calculateWithMemoization(double presentValue,
                                           double growthRate,
                                           int years) {
        memo = new double[years + 1];
        return memoHelper(presentValue, growthRate, years);
    }

    private double memoHelper(double presentValue,
                              double growthRate,
                              int years) {
        // Base case
        if (years == 0) {
            return presentValue;
        }

        // Already calculated? return stored value!
        if (memo[years] != 0) {
            System.out.println("Using cached value for year: " + years);
            return memo[years];
        }

        // Calculate and store
        memo[years] = memoHelper(presentValue, growthRate, years - 1)
                * (1 + growthRate);
        return memo[years];
    }
}