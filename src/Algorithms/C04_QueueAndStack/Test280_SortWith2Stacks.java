package Algorithms.C04_QueueAndStack;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Random;

public class Test280_SortWith2Stacks {
    /**
     * use a count to record the un-processed elements
     * input stack is the result stack as well
     * In LaiCode, it ask for top to bottom ascending order
     *  which means to find max value every time
     **/
    public void sortWith2StacksI(Deque<Integer> in) {
        Deque<Integer> buf = new LinkedList<>();

        int count = in.size();
        int count_min = 0;

        // Stop when un-processed elements amounts are 0
        while (count != 0) {
            int min = Integer.MAX_VALUE;

            // step 1: check every un-processed elements
            // record the min value and count_min value
            for (int i = 0; i < count; i++) {
                int tmp = in.pollFirst();
                if (tmp < min) {
                    min = tmp;
                    count_min = 1;
                } else if (min == tmp) {
                    count_min++;
                }
                buf.offerFirst(tmp);
            }

            // step 2: add the min to input(result) min_count times
            for (int i = 0; i < count_min; i++) {
                in.offerFirst(min);
                count--;
            }

            // step 3: move back unprocessed elements to input stack
            while (buf.size() > 0) {
                int tmp = buf.pollFirst();
                if (tmp != min) {
                    in.offerFirst(tmp);
                }
            }
        }
    }



    /**
     * do not use a count to record the un-processed elements
     * input stack is the result stack as well
     * buffer stack store un-processed elements
     **/
    public void sortWith2StacksII(Deque<Integer> in) {
        Deque<Integer> buf = new LinkedList<>();

        // move all un-processed elements to buffer stack first
        while (in.size() > 0) {
            buf.offerFirst(in.pollFirst());
        }

        int min;
        int count_min;

        // while there are still un-processed elements
        while (buf.size() > 0) {
            min = Integer.MAX_VALUE;
            count_min = 0;

            // step 1: check every un-processed elements
            // record min and count_min
            while (buf.size() > 0) {
                int tmp = buf.pollFirst();
                if (tmp < min) {
                    min = tmp;
                    count_min = 1;
                } else if (tmp == min) {
                    count_min++;
                }
                in.offerFirst(tmp);
            }

            // step 2: move back un-processed elements to buffer
            // continue when
            //     input stack has elements and
            //     the element peek from input stack in unprocessed element

            while (in.size() > 0 && in.peekFirst() >= min) {
                int tmp = in.pollFirst();
                if (tmp > min) {
                    buf.offerFirst(tmp);
                }
            }

            // step 3: store the sorted elements in input/result buffer
            for (int i=0; i<count_min; i++) {
                in.offerFirst(min);
            }
        }
    }


    public static void main(String[] args) {
        Random rand = new Random();
        Deque<Integer> in = new LinkedList<>();
        for (int i = 0; i <= 10; i++) {
            int num = rand.nextInt(100);
            in.offerFirst(num);
            if (i < 10) {
                System.out.print(num + "->");
            } else {
                System.out.println(num + "");
            }
        }


        Test280_SortWith2Stacks test = new Test280_SortWith2Stacks();
        test.sortWith2StacksII(in);
        System.out.print("top->");
        while (in.size() > 0) {
            if (in.size() == 1) {
                System.out.print(in.pollFirst());
            } else {
                System.out.print(in.pollFirst()+"->");
            }
        }
        System.out.println("->bottom");
    }

}
