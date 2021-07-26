package Algorithms.C02_Recursion1AndSortingAlgorithms;

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

    /**
     * Binary search to find the power
     * use iteration
     * @param a
     * @param b
     * @return
     */
    public long powerIteration(int a, int b) {
        if (b == 0) {
            return 1;
        }

        int remain = b;
        long result = 1;
        while (remain > 0) {
            int count = 1;
            long power = a;
            while ((count << 1) <= remain) {
                power *= power;
                count <<= 1;
            }
            result *= power;
            remain -= count;
        }
        return result;
    }

    public static void main(String[] args) {
        Test13_Power test13_power = new Test13_Power();
        System.out.println(test13_power.powerIteration(0, 10));
    }
}
