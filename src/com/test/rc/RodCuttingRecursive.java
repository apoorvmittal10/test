package com.test.rc;

/**
 * Created by apoorv on 18/03/17.
 */
public class RodCuttingRecursive {

    /* Driver program to test above functions */
    public static void main(String args[]) {
        int arr[] = new int[]{1, 1, 8, 9, 10, 18, 17, 10};
        int size = arr.length;
        System.out.println("Maximum Obtainable Value is " +
                cutRod(arr, size));

    }

    /* Returns the best obtainable price for a rod of length
       n and price[] as prices of different pieces */
    static int cutRod(int price[], int n) {
        if (n <= 0)
            return 0;
        int max_val = Integer.MIN_VALUE;

        // Recursively cut the rod in different pieces and
        // compare different configurations
        for (int i = 0; i < n; i++)
            max_val = Math.max(max_val,
                    price[i] + cutRod(price, n - i - 1));

        return max_val;
    }

}
