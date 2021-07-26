package Algorithms.C04_QueueAndStack;

import java.util.Deque;
import java.util.LinkedList;

public class Test31_QueueByTwoStacks {
    /**
     * Implement a Queue class with 2 stacks
     * [ 1 2 3 ← in
     * [ 4 5 6 → out
     * head → 3 2 1]middle of queue[4 5 6 → tail
     **/
    Deque<Integer> in;
    Deque<Integer> out;

    public Test31_QueueByTwoStacks() {
        in = new LinkedList<>();
        out = new LinkedList<>();
    }

    private void shuffleIfNecessary() {
        if (out.size() == 0) {
            while (in.size() > 0) {
                out.offerFirst(in.poll());
            }
        }
    }

    public Integer poll() {
        shuffleIfNecessary();
        if (out.size() == 0) {
            return null;
        }
        return out.pollFirst();
    }

    public void offer(int element) {
        in.offerFirst(element);
    }

    public Integer peek() {
        shuffleIfNecessary();
        if (out.size() == 0) {
            return null;
        }
        return out.peekFirst();
    }

    public int size() {
        return in.size() + out.size();
    }

    public boolean isEmpty() {
        return in.isEmpty() && out.isEmpty();
    }
}
