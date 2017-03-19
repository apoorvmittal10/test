package com.test.houses;

/**
 * Created by apoorv on 19/03/17.
 */
public class HousesDP {

    public static void main(String[] args) {
        int[] data = {6, 1, 2, 7, 10, 5, 14, 6, 9, 15, 14, 5, 4, 10, 11};
        System.out.println(houses(data, data.length));
    }

    private static int houses(int[] data, int n) {
        if (n == 0) {
            return 0;
        }

        int value1 = data[0];

        if (n == 1) {
            return value1;
        }

        int value = 0;
        int value2 = data[1];
        if (n == 2) {
            value = value1 > value2 ? value1 : value2;
        }

        for (int i = 2; i < n; i++) {
            value = Math.max(value2, value1 + data[i]);
            value1 = value2;
            value2 = value;
        }
        return value;
    }
}
