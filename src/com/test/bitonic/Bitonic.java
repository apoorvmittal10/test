package com.test.bitonic;

/**
 * Created by apoorv on 19/03/17.
 */
public class Bitonic {

    public static void main(String[] args) {
        int[] data = {0, 8, 4, 12, 2, 10, 6, 14, 1, 9, 5, 13, 3, 11, 7, 15};
        System.out.println(maximumSum(data, data.length));
    }

    private static int maximumSum(int[] data, int n) {
        if (n == 0) {
            return 0;
        }

        if (n == 1) {
            return data[0];
        }

        int[] L = new int[n];
        int[] D = new int[n];
        for (int i = 0; i < n; i++) {
            L[i] = 1;
            D[i] = 1;
        }

        for (int i = 1; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (data[i] > data[j] && L[i] < L[j] + 1) {
                    L[i] = L[j] + 1;
                }

                if (data[i] < data[j] && D[i] < D[j] + 1) {
                    D[i] = D[j] + 1;
                }
            }
        }

        int max = 0;
        for (int i = 0; i < L.length; i++) {
            if (L[i] + D[i] - 1 > max) {
                max = L[i] + D[i] - 1;
            }
        }

        return max;
    }
}
