package com.dsa.search;

public class ProductSearchTest {

    public static void main(String[] args) {

        ProductSearch searcher = new ProductSearch();

        System.out.println("========================================");
        System.out.println("      LINEAR SEARCH DEMONSTRATION");
        System.out.println("========================================\n");

        Product[] unsortedProducts = {
                new Product(103, "Laptop", "Electronics"),
                new Product(101, "Phone", "Electronics"),
                new Product(105, "Shoes", "Fashion"),
                new Product(102, "Watch", "Accessories"),
                new Product(104, "Bag", "Fashion")
        };

        System.out.println("Searching in UNSORTED array:");
        Product result1 = searcher.linearSearch(unsortedProducts, 104);
        if (result1 != null) {
            System.out.println("Result: " + result1);
        } else {
            System.out.println("Product not found!");
        }

        System.out.println();

        // Search for non-existing product
        Product result2 = searcher.linearSearch(unsortedProducts, 999);
        if (result2 != null) {
            System.out.println("Result: " + result2);
        } else {
            System.out.println("Product not found!");
        }

        System.out.println("\n========================================");
        System.out.println("      BINARY SEARCH DEMONSTRATION");
        System.out.println("========================================\n");

        // SORTED by productId - mandatory for binary search!
        Product[] sortedProducts = {
                new Product(101, "Phone", "Electronics"),
                new Product(102, "Watch", "Accessories"),
                new Product(103, "Laptop", "Electronics"),
                new Product(104, "Bag", "Fashion"),
                new Product(105, "Shoes", "Fashion"),
                new Product(106, "Tablet", "Electronics"),
                new Product(107, "Headphones", "Electronics")
        };

        System.out.println("Searching in SORTED array:");
        Product result3 = searcher.binarySearch(sortedProducts, 105);
        if (result3 != null) {
            System.out.println("Result: " + result3);
        } else {
            System.out.println("Product not found!");
        }

        System.out.println();

        // Search for non-existing product
        Product result4 = searcher.binarySearch(sortedProducts, 999);
        if (result4 != null) {
            System.out.println("Result: " + result4);
        } else {
            System.out.println("Product not found!");
        }

        // ==========================================
        // COMPLEXITY ANALYSIS
        // ==========================================
        System.out.println("\n========================================");
        System.out.println("         COMPLEXITY ANALYSIS");
        System.out.println("========================================");
        System.out.println("Linear Search:");
        System.out.println("  Best Case:    O(1) - found at first position");
        System.out.println("  Average Case: O(n/2) = O(n)");
        System.out.println("  Worst Case:   O(n) - found at last or not found");
        System.out.println("  Requires sorted array: NO");
        System.out.println();
        System.out.println("Binary Search:");
        System.out.println("  Best Case:    O(1) - found at middle");
        System.out.println("  Average Case: O(log n)");
        System.out.println("  Worst Case:   O(log n)");
        System.out.println("  Requires sorted array: YES");
        System.out.println();
        System.out.println("For E-commerce Platform:");
        System.out.println("  Binary Search is BETTER for large product catalogs");
        System.out.println("  1000 products: Linear needs 1000 steps");
        System.out.println("                 Binary needs only 10 steps (log2 1000 ≈ 10)");
    }
}