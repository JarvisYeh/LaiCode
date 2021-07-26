package Algorithms.C04_QueueAndStack;

import java.util.Deque;
import java.util.LinkedList;

/**
 * Use a middle stack as buffer
 * if left is empty
 * move half of right to buffer
 * then move buffer to left
 * so that the order is correct
 * left ][ 1 2 3 4 5 6 7 8 right  =>
 * left ][ 1 2 3 4 right & buf[ 8 7 6 5 =>
 * left 1 2 3 4 ][ right & buf[ 8 7 6 5 =>
 * left 1 2 3 4 ][ 5 6 7 8 right & buf[
 */
public class Test645_DequeByThreeStacks {
    Deque<Integer> left;
    Deque<Integer> right;
    Deque<Integer> buf;

    public Test645_DequeByThreeStacks() {
        left = new LinkedList<>();
        right = new LinkedList<>();
        buf = new LinkedList<>();
    }

    // shuffle half elements from two to one
    // with the help of buffer to main the order
    private void shuffleHalfIfNecessary(Deque<Integer> one, Deque<Integer> two) {
        if (one.isEmpty()) {
            // shuffle 1/2 elements from stack two to buffer
            // if two.size == 1, move 1/2 = 0 element to buffer
            // the only element will then be moved to stack one
            for (int i = 0; i < two.size() / 2; i++) {
                one.offerFirst(two.pollFirst());
            }

            // shuffle the rest elements from stack two to one
            while (!two.isEmpty()) {
                one.offerFirst(two.pollFirst());
            }

            // restore elements from buffer to two
            while (!buf.isEmpty()) {
                two.offerFirst(buf.pollFirst());
            }
        }
    }

    public void offerFirst(int element) {
        left.offerFirst(element);
    }

    public void offerLast(int element) {
        right.offerFirst(element);
    }

    public Integer pollFirst() {
        if (isEmpty()) {
            return null;
        }
        shuffleHalfIfNecessary(left, right);
        return left.pollFirst();
    }

    public Integer pollLast() {
        if (isEmpty()) {
            return null;
        }
        shuffleHalfIfNecessary(right, left);
        return right.pollFirst();
    }

    public Integer peekFirst() {
        if (isEmpty()) {
            return null;
        }
        shuffleHalfIfNecessary(left, right);
        return left.peekFirst();
    }

    public Integer peekLast() {
        if (isEmpty()) {
            return null;
        }
        shuffleHalfIfNecessary(right, left);
        return right.peekFirst();
    }

    public int size() {
        return left.size() + right.size();
    }

    public boolean isEmpty() {
        return left.isEmpty() && right.isEmpty();
    }
}
