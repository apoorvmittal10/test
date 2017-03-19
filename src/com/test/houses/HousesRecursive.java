package com.test.houses;

/**
 * Created by apoorv on 19/03/17.
 */
public class HousesRecursive {

    public static void main(String[] args) {
        int[] data = {6, 1, 2, 7, 10, 5, 14, 6, 9, 15, 14, 5, 4, 10, 11};
        System.out.println(houses(data, data.length));
    }

    private static int houses(int[] data, int n) {
        if (n == 0) {
            return 0;
        }

        if (n == 1) {
            return data[0];
        }

        if (n == 2) {
            return data[0] > data[1] ? data[0] : data[1];
        }

        return Math.max(data[n-1] + houses(data, n - 2), houses(data, n - 1));
    }
}
