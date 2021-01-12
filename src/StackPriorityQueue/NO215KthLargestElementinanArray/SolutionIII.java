package StackPriorityQueue.NO215KthLargestElementinanArray;

import java.util.PriorityQueue;

/**
 * @author zzj
 * @version 1.0
 * @date 2020/12/23 16:16
 * @description
 */
public class SolutionIII {

    public int findKthLargest(int[] nums, int k) {
        PriorityQueue<Integer> p = new PriorityQueue<>();
        for (int n : nums) {
            if (p.size() < k) {
                p.add(n);
            } else {
                if (n > p.peek()) {
                    p.poll();
                    p.add(n);
                }
            }
        }
        return p.poll();
    }
}
