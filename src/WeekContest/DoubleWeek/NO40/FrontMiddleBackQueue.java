package WeekContest.DoubleWeek.NO40;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * @author zzj
 * @version 1.0
 * @date 2020/11/28 22:59
 * @description
 */
public class FrontMiddleBackQueue {

    private Deque<Integer> queue1 = new ArrayDeque<>();
    private Deque<Integer> queue2 = new ArrayDeque<>();

    public FrontMiddleBackQueue() {

    }

    public void pushFront(int val) {
        queue1.addFirst(val);
    }

    public void pushMiddle(int val) {
        balance();
        int v = queue1.size() - queue2.size();
        if (v == 0) {
            queue2.addFirst(val);
        } else {
            queue1.addLast(val);
        }

    }

    private void balance() {
        int v = queue1.size() - queue2.size();
        if (v == 0 || v == -1) {
            return;
        }
        if (v > 0) {
            queue2.addFirst(queue1.pollLast());
            balance();
        }
        if (v < -1) {
            queue1.addLast(queue2.pop());
            balance();
        }
    }

    public void pushBack(int val) {
        queue2.addLast(val);
    }

    public int popFront() {
        if (queue1.size() + queue2.size() == 0) {
            return -1;
        }
        if (!queue1.isEmpty()) {
            return queue1.pop();
        }
        return queue2.pop();
    }

    public int popMiddle() {
        if (queue1.size() + queue2.size() == 0) {
            return -1;
        }
        balance();
        int v = queue1.size() - queue2.size();
        if (v == 0) {
            return queue1.pollLast();
        }
        return queue2.pop();
    }

    public int popBack() {
        if (queue1.size() + queue2.size() == 0) {
            return -1;
        }
        if (!queue2.isEmpty()) {
            return queue2.pollLast();
        }
        return queue1.pollLast();
    }

}
