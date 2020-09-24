package Algorithms.C2_Recursion1AndSortingAlgorithms;

public class Test624_FibonacciNumber {
    /**
     * User recursion to solve the problem
     * Time Complexity: O(2^n)
     * Space Complexity: O(n)
     * 	      F(4)
     * 	      / \
     *    F(3)       F(2)
     *  /    \       /   \
     * F(2)   F(1)  F(1) F(0)
     *  /   \
     * F(1) F(0)
     **/
    public int fibo(int n) {
        // base case
        if (n == 0) {
            return 0;
        } else if (n == 1) {
            return 1;
        }

        return fibo(n-1) + fibo(n-2);
    }


    public static void main(String[] args) {
        Test624_FibonacciNumber test = new Test624_FibonacciNumber();
        System.out.println(test.fibo(2));
    }
}
