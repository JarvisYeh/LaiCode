package Practice.P04_QueueAndStack;

public class QueueUsingArray {
    private int[] array;
    private int head;
    private int tail;
    private int size;

    public QueueUsingArray(int capacity) {
        array = new int[capacity];
        head = tail = 0;
    }

    public boolean offer(int i) {
        if (size == array.length) {
            return false;
        }
        array[tail] = i;
        tail = (tail + 1) % array.length;
        size++;
        return true;
    }

    public Integer poll() {
        if (size == 0) {
            return null;
        }
        Integer toBeReturn = array[head];
        head = (head + 1) % array.length;
        size--;
        return toBeReturn;
    }

    public Integer peek() {
        if (size == 0) {
            return null;
        }
        return array[head];
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }
}
