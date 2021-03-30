package StackPriorityQueue.NO628MaximumProductofThreeNumbers;

import java.util.PriorityQueue;

/**
 * @author zzj
 * @version 1.0
 * @date 2021/1/20 15:00
 * @description
 */
public class Solution {

    public int maximumProduct(int[] nums) {
        //维护最大的三个数
        PriorityQueue<Integer> q = new PriorityQueue<>();
        //维护最小的三个数
        PriorityQueue<Integer> p = new PriorityQueue<>((o1, o2) -> o2 - o1);

        for (int n : nums) {
            if (q.size() < 3) {
                q.add(n);
            } else if (n > q.peek()) {
                q.poll();
                q.add(n);
            }
            if (p.size() < 3) {
                p.add(n);
            } else if (n < p.peek()) {
                p.poll();
                p.add(n);
            }
        }
        int a = q.poll();
        int b = q.poll();
        int c = q.poll();
        p.poll();
        return Math.max(p.poll() * p.poll() * c, a * b * c);
    }

}
