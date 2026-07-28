package com.tdd;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LoggingExample {

    // Create logger for this class
    private static final Logger logger =
            LoggerFactory.getLogger(LoggingExample.class);

    public static void main(String[] args) {

        System.out.println("=== SLF4J Logging Demo ===\n");

        // TRACE - most detailed level
        logger.trace("TRACE: Application starting up...");

        // DEBUG - debugging information
        logger.debug("DEBUG: Loading configuration files...");

        // INFO - general information (most common)
        logger.info("INFO: Application started successfully!");
        logger.info("INFO: User {} logged in from {}", "Aryan", "Kolkata");

        // WARN - something might be wrong
        logger.warn("WARN: Low memory detected - {}MB remaining", 256);
        logger.warn("WARN: Database connection pool running low!");

        // ERROR - something went wrong
        logger.error("ERROR: Payment failed for transaction ID: {}", 12345);
        logger.error("ERROR: Unable to connect to external API!");

        // Logging with exception
        try {
            int result = 10 / 0;
        } catch (ArithmeticException e) {
            logger.error("ERROR: Arithmetic exception occurred: {}",
                    e.getMessage(), e);
        }

        // Real world scenario - Bank transaction
        System.out.println("\n=== Bank Transaction Logging ===\n");
        processTransaction(1001, 5000);
        processTransaction(1002, -100);
        processTransaction(1003, 999999);
    }

    public static void processTransaction(int accountId, double amount) {
        logger.info("Processing transaction for Account: {}", accountId);

        if (amount < 0) {
            logger.warn("WARN: Negative amount {} detected for account {}",
                    amount, accountId);
            return;
        }

        if (amount > 100000) {
            logger.warn("WARN: Large transaction {} detected for account {}",
                    amount, accountId);
        }

        try {
            if (amount > 500000) {
                throw new Exception("Transaction limit exceeded!");
            }
            logger.info("Transaction successful! Account: {} Amount: ${}",
                    accountId, amount);

        } catch (Exception e) {
            logger.error("ERROR: Transaction failed for account {}: {}",
                    accountId, e.getMessage());
        }
    }
}
