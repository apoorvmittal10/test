package com.test.rc;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by apoorv on 18/03/17.
 */
public class RodCuttingDP {

    /* Driver program to test above functions */
    public static void main(String args[]) {
        int arr[] = new int[]{1, 5, 8, 9, 10, 17, 17, 20};
        int size = arr.length;
        System.out.println("Maximum Obtainable Value is " +
                cutRod(arr, size));
    }

    /* Returns the best obtainable price for a rod of
       length n and price[] as prices of different pieces */
    static int cutRod(int price[], int n) {
        int val[] = new int[n + 1];
        val[0] = 0;

        // Build the table val[] in bottom up manner and return
        // the last entry from the table
        for (int i = 1; i <= n; i++) {
            int max_val = Integer.MIN_VALUE;
            for (int j = 0; j < i; j++)
                max_val = Math.max(max_val, price[j] + val[i - j - 1]);
            val[i] = max_val;
        }

        return val[n];
    }

    static int cutRodMap(int price[], int n) {
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, 0);

        // Build the table val[] in bottom up manner and return
        // the last entry from the table
        for (int i = 1; i <= n; i++) {
            int max_val = Integer.MIN_VALUE;
            for (int j = 0; j < i; j++)
                max_val = Math.max(max_val, price[j] + map.get(i - j - 1));
            map.put(i, max_val);
        }

        return map.get(n);
    }

}
