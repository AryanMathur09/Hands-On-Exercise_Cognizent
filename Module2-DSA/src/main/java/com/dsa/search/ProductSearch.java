package com.dsa.search;

public class ProductSearch {


    public Product linearSearch(Product[] products, int targetId) {
        System.out.println("Performing Linear Search for productId: " + targetId);

        for (int i = 0; i < products.length; i++) {
            // Check every element
            if (products[i].getProductId() == targetId) {
                System.out.println("Found at index: " + i);
                return products[i];
            }
        }
        return null; // not found
    }

    public Product binarySearch(Product[] sortedProducts, int targetId) {
        System.out.println("Performing Binary Search for productId: " + targetId);

        int left = 0;
        int right = sortedProducts.length - 1;
        int steps = 0;

        while (left <= right) {
            steps++;
            int mid = left + (right - left) / 2; // find middle index

            System.out.println("Step " + steps +
                    ": Checking index " + mid +
                    " (productId: " + sortedProducts[mid].getProductId() + ")");

            // Found it!
            if (sortedProducts[mid].getProductId() == targetId) {
                System.out.println("Found at index: " + mid +
                        " in " + steps + " steps!");
                return sortedProducts[mid];
            }

            // Target is in RIGHT half
            if (sortedProducts[mid].getProductId() < targetId) {
                left = mid + 1;
                System.out.println("Target is in RIGHT half");
            }
            // Target is in LEFT half
            else {
                right = mid - 1;
                System.out.println("Target is in LEFT half");
            }
        }
        return null; // not found
    }
}
