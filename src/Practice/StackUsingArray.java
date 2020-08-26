package Practice;

public class StackUsingArray {
    private int[] array;
    private int head;   // point to the top of the stack

    public StackUsingArray(int capacity) {
        array = new int[capacity];
        head = -1;
    }

    public boolean push(int i ) {
        if (head + 1 == array.length) {
            return false;
        }
        array[++head] = i;
        return true;
    }

    public Integer pop() {
        if (head == -1) {
            return null;
        }
        return array[head--];
    }

    public Integer top() {
        if (head == -1) {
            return null;
        }
        return array[head];
    }

    public int size() {
        return head + 1;
    }

    public boolean isEmpty() {
        return head == -1;
    }
}
