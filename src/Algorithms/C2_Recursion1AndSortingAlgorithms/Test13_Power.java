package Algorithms.C2_Recursion1AndSortingAlgorithms;

public class Test13_Power {
    /**
     * Returns a to the power of b
     * Assume b is a positive integer
     * Assume no overflow
     * Time Complexity: O(log b)
     * Space Complexity: O(log b)
     * P(2, 8)
     *    |
     * P(2, 4)
     *    |
     * P(2, 2)
     *    |
     * P(2, 1)
     *    |
     * P(2, 0)
     **/
    public int power(int a, int b) {
        // base case
        if (b == 0) {
            return 1;
        }

        // recursive rule
        int half = power(a, b / 2);
        if (b % 2 == 0) {
            return half * half;
        } else {
            return half * half * a;
        }
    }

    public static void main(String[] args) {
        Test13_Power test13_power = new Test13_Power();
        System.out.println(test13_power.power(2, 10));
    }
}
