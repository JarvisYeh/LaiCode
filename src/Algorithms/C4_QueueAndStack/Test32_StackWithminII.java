package Algorithms.C4_QueueAndStack;

import util.Entry;

import java.util.Deque;
import java.util.LinkedList;

/**
 * min stack stores <min_value, index>
 * everytime there comes a new min_value, record its value and index pair
 * everytime pop out one value, check if it's the min_value index, if is, pop that pair from min as well
 */
public class Test32_StackWithminII {
    Deque<Integer> stack;
    Deque<Entry<Integer, Integer>> min;

    public Test32_StackWithminII() {
        stack = new LinkedList<>();
        min = new LinkedList<>();
    }

    public Integer pop() {
        if (stack.size() == 0) {
            return null;
        }
        if (stack.size() - 1 == min.peekFirst().getValue()) {
            min.pollFirst();
        }
        return stack.pollFirst();
    }

    // <minValue, index of that value>
    public void push(int element) {
        stack.offerFirst(element);
        if (min.size() == 0 || element < min.peekFirst().getKey()) {
            min.offerFirst(new Entry<>(element, stack.size() - 1));
        }
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
        return min.peekFirst().getKey();
    }

    public static void main(String[] args) {
        Test32_StackWithminII stackWithMin = new Test32_StackWithminII();
        stackWithMin.push(6);
        System.out.print(stackWithMin.min() + "->"); //136
        stackWithMin.push(5);
        stackWithMin.push(9);
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
