package com.test.msis;

import java.util.Arrays;

/**
 * Created by apoorv on 19/03/17.
 */
public class MaximumSumIncrSubSequence {

    public static void main(String[] args) {
        int[] data = {1, 101, 2, 4, 3, 5, 6, 1, 3, 100, 4, 5};
        System.out.println(maximumSum(data, data.length));
    }

    private static int maximumSum(int[] data, int n) {
        if (n == 0) {
            return 0;
        }

        if (n == 1) {
            return data[0];
        }

        int L[] = new int[n];
        for (int i = 0; i < n; i++) {
            L[i] = data[i];
        }

        L[0] = data[0];
        for (int i = 1; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (data[i] > data[j] && L[i] < L[j] + data[i]) {
                    L[i] = L[j] + data[i];
                }
            }
        }

        int max = 0;
        for (int i = 0; i < L.length; i++) {
            if (L[i] > max) {
                max = L[i];
            }
        }

        return max;
    }
}
