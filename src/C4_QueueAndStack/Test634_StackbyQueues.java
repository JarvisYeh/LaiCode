package C4_QueueAndStack;

import java.util.Deque;
import java.util.LinkedList;

/**
 * in: <- 1 2 3 4 <-
 * <p>
 * in: <- 4 <-
 * buf: <- 1 2 3 <-
 * <p>
 * poll 4, switch ref
 * buf: <- <-
 * in: <- 1 2 3<-
 **/
public class Test634_StackbyQueues {
    Deque<Integer> in;
    Deque<Integer> buf;

    public Test634_StackbyQueues() {
        in = new LinkedList<>();
        buf = new LinkedList<>();
    }

    private void swapRef() {
        Deque<Integer> tmp = in;
        in = buf;
        buf = tmp;
    }

    public void push(int x) {
        in.offerFirst(x);
    }

    public Integer pop() {
        if (in.size() == 0) {
            return null;
        }
        while (!in.isEmpty()) {
            if (in.size() == 1) {
                int tmp = in.pollLast();
                swapRef();
                return tmp;
            } else {
                buf.offerFirst(in.pollLast());
            }
        }
        return null;
    }

    public Integer top() {
        if (in.size() == 0) {
            return null;
        }
        int tmp = 0;
        while (!in.isEmpty()) {
            tmp = in.pollLast();
            buf.offerFirst(tmp);
        }
        swapRef();
        return tmp;
    }

    public boolean isEmpty() {
        return in.isEmpty();
    }
}
