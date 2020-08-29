package Practice.P4_QueueAndStack;

// elements range from (head, tail)
public class ResizableQueueUsingArray {
    private int[] array;
    private int head;
    private int tail;

    public ResizableQueueUsingArray(int capacity) {
        array = new int[capacity];
        head = 0;
        tail = 1;
    }

    private void expandArray() {
        int newSize = (int) (array.length * 1.5);
        int[] newArray = new int[newSize];
        int j = 0;
        for (int i=0; i < array.length ;i++) {
            newArray[j++] = array[(i + head) % array.length];
        }
        array = newArray;
        head = 0;
        tail = j;
    }

    public boolean offer(int value) {
        if (head == tail) {
            expandArray();
        }
        array[tail] = value;
        tail = (tail + 1) % array.length;
        return true;
    }

    public Integer poll() {
        int index = (head + 1) % array.length;
        if (index == tail) {
            return null;
        }
        Integer toBeReturn = array[index];
        head = index;
        return toBeReturn;
    }

    public Integer peek() {
        int index = (head + 1) % array.length;
        if (index == tail) {
            return null;
        }
        return array[index];
    }

    public int size() {
        if (head < tail) {
            return tail - head - 1;
        } else {
            return array.length - head;
        }
    }

    public boolean isEmpty() {
        return (head + 1) % array.length == tail;
    }
}
