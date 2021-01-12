package StackPriorityQueue.NO347TopKFrequentElements;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * @author zzj
 * @version 1.0
 * @date 2020/12/23 12:37
 * @description
 */
public class Solution {

    public int[] topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> nToC = new HashMap<>();
        for (int n : nums) {
            nToC.put(n, nToC.getOrDefault(n, 0) + 1);
        }
        Queue<Integer> q = new PriorityQueue<>((o1, o2) -> nToC.get(o2) - nToC.get(o1));
        for (int i : nToC.keySet()) {
            q.add(i);
        }
        int[] res = new int[k];
        for (int i = 0; i < k; i++) {
            res[i] = q.poll();
        }
        return res;
    }
}
