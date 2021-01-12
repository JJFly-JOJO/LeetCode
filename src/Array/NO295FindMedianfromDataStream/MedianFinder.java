package Array.NO295FindMedianfromDataStream;

import java.util.PriorityQueue;
import java.util.Queue;

/**
 * @author zzj
 * @version 1.0
 * @date 2020/10/21 9:23
 * @description addNum(1)
 * addNum(2)
 * findMedian() -> 1.5
 * addNum(3)
 * findMedian() -> 2
 */
public class MedianFinder {

    /**
     * leftHeap为最大堆 rightHeap为最小堆-------------并没有必要对整个数组进行排序 我们只关心在中间的那两个数（或者一个数），其它数没有必要进行 “比较” 和 “交换” 的操作----------
     */
    private Queue<Integer> leftHeap;
    private Queue<Integer> rightHeap;
    /**
     * initialize your data structure here.
     */
    public MedianFinder() {
        leftHeap = new PriorityQueue<>(8, (o1, o2) -> (o2 - o1));
        rightHeap = new PriorityQueue<>(8, (o1, o2) -> (o1 - o2));
    }

    public static void main(String[] args) {
        MedianFinder medianFinder = new MedianFinder();
        medianFinder.addNum(6);
        medianFinder.addNum(10);
        medianFinder.addNum(2);
        medianFinder.addNum(6);
        medianFinder.addNum(5);
        medianFinder.addNum(0);
        medianFinder.addNum(6);
        System.out.println(medianFinder.findMedian());
    }

    public void addNum(int num) {
        //两个堆都为空
        if (leftHeap.isEmpty() && rightHeap.isEmpty()) {
            leftHeap.add(num);
            return;
        }
        //与两个堆比较
        if (num < leftHeap.peek()) {
            leftHeap.add(num);
        } else {
            rightHeap.add(num);
        }
        //平衡堆
        int diff = leftHeap.size() - rightHeap.size();
        if (diff == 2) {
            rightHeap.add(leftHeap.poll());
        } else if (diff == -2) {
            leftHeap.add(rightHeap.poll());
        }
    }

    public double findMedian() {
        if (leftHeap.isEmpty() && rightHeap.isEmpty()) {
            return 0;
        }
        if (leftHeap.size() == rightHeap.size()) {
            //(leftHeap.peek() + rightHeap.peek()) / 2<------易错点！！！！
            return (leftHeap.peek() + rightHeap.peek()) / 2.0;
        }
        return leftHeap.size() > rightHeap.size() ? leftHeap.peek() : rightHeap.peek();
    }

}
