package C4_QueueAndStack;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Use one queue to implement stack
 * pop: poll and push (size - 1) times, then pop
 * peek: poll and push size times, return the last poll and push value
 */
public class Test_StackbyQueue {
    Queue<Integer> in;

    public Test_StackbyQueue() {
        in = new LinkedList<>();
    }

    public void push(int x) {
        in.offer(x);
    }


    public Integer pop() {
        if (in.size() == 0) {
            return null;
        }
        int size = in.size();
        for (int i = 0; i < size - 1; i++) {
            in.offer(in.poll());
        }
        return in.poll();
    }


    public Integer top() {
        if (in.size() == 0) {
            return null;
        }
        int size = in.size();
        int tmp = 0;
        for (int i = 0; i < size; i++) {
            tmp = in.poll();
            in.offer(tmp);
        }
        return tmp;
    }


    public boolean isEmpty() {
        return in.isEmpty();
    }
}
