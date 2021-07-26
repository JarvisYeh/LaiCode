package Algorithms.C04_QueueAndStack;

import java.util.Deque;
import java.util.LinkedList;

public class Test32_StackWithminI {
    Deque<Integer> stack;
    Deque<Integer> min;

    public Test32_StackWithminI() {
        stack = new LinkedList<>();
        min = new LinkedList<>();
    }

    public Integer pop() {
        if (stack.isEmpty()) {
            return null;
        }
        if (stack.peekFirst().equals(min.peekFirst())) {
            min.pollFirst();
        }
        return stack.pollFirst();
    }

    public void push(int element) {
        if (min.size() == 0 || element <= min.peekFirst()) {
            min.offerFirst(element);
        }
        stack.offerFirst(element);
    }

    public Integer top() {
        if (stack.size() == 0) {
            return null;
        }
        return stack.peekFirst();
    }

    public Integer min() {
        if (min.size() == 0) {
            return null;
        }
        return min.peekFirst();
    }

    public static void main(String[] args) {
        Test32_StackWithminI stackWithMin = new Test32_StackWithminI();
        stackWithMin.push(136);
        System.out.print(stackWithMin.min() + "->"); //136
        stackWithMin.push(135);
        stackWithMin.push(139);
        System.out.print(stackWithMin.top() + "->"); //139
        System.out.print(stackWithMin.min() + "->");  //135
        System.out.print(stackWithMin.pop() + "->"); //139
        System.out.print(stackWithMin.min() + "->"); //135
        System.out.print(stackWithMin.top() + "->"); //135
        System.out.print(stackWithMin.min() + "->");  //135
        System.out.print(stackWithMin.pop() + "->"); //135
        System.out.print(stackWithMin.min()); //136
    }
}