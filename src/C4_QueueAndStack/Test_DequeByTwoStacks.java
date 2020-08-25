package C4_QueueAndStack;

import java.util.Deque;
import java.util.LinkedList;

/**
 * Amortized complexity for calling pollFirst() pollLast() alternatively is
 * O([(n-1) + (n-1) +...+ 1]/n) = O(n), Need shuffle everytime
 * left : 1 2 3 4 5][6 7 8 9 10 : right
 */
public class Test_DequeByTwoStacks {
    Deque<Integer> left;
    Deque<Integer> right;

    public Test_DequeByTwoStacks() {
        left = new LinkedList<>();
        right = new LinkedList<>();
    }

    // when there are no elements in stack one, shuffle every elements in stack two to stack one
    private void shuffleIfNecessary(Deque<Integer> one, Deque<Integer> two) {
        if (one.isEmpty()) {
            while (!two.isEmpty()) {
                one.offerFirst(two.pollFirst());
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
        shuffleIfNecessary(left, right);
        return left.pollFirst();
    }

    public Integer pollLast() {
        if (isEmpty()) {
            return null;
        }
        shuffleIfNecessary(right, left);
        return right.pollFirst();
    }

    public Integer peekFirst() {
        if (isEmpty()) {
            return null;
        }
        shuffleIfNecessary(left, right);
        return left.peekFirst();
    }

    public Integer peekLast() {
        if (isEmpty()) {
            return null;
        }
        shuffleIfNecessary(right, left);
        return right.peekFirst();
    }

    public int size() {
        return left.size() + right.size();
    }

    public boolean isEmpty() {
        return left.isEmpty() && right.isEmpty();
    }

}
