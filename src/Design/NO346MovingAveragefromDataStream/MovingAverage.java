package Design.NO346MovingAveragefromDataStream;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * @author zzj
 * @version 1.0
 * @date 2020/11/2 16:52
 * @description
 */
public class MovingAverage {

    private Deque<Integer> deque;

    private int sum;

    private int windowSize;

    /**
     * Initialize your data structure here.
     */
    public MovingAverage(int size) {
        deque = new ArrayDeque<>(size);
        sum = 0;
        windowSize = size;
    }

    public double next(int val) {
        sum += val;
        if (deque.size() == windowSize) {
            sum -= deque.poll();
        }
        deque.add(val);
        return (double) sum / deque.size();
    }

}
