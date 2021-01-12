package StackPriorityQueue.NO347TopKFrequentElements;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * @author zzj
 * @version 1.0
 * @date 2020/12/23 13:37
 * @description --------------堆只维护前K个元素 注意堆操作 从堆顶弹出元素是O(1) 插入元素是O(lgn)
 */
public class SolutionII {

    public int[] topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> nToC = new HashMap<>();
        for (int n : nums) {
            nToC.put(n, nToC.getOrDefault(n, 0) + 1);
        }
        PriorityQueue<Integer> q = new PriorityQueue<>(Comparator.comparingInt(nToC::get));
        for (int n : nToC.keySet()) {
            if (q.size() < k) {
                q.add(n);
            } else {
                if (nToC.get(n) > nToC.get(q.peek())) {
                    //O(logk)复杂度 因为是将尾部元素放在堆顶重新调整堆 保证堆数组不会出现空缺位
                    q.poll();
                    //O(logk)的复杂度
                    q.add(n);
                }
            }
        }
        int[] res = new int[k];
        for (int i = 0; i < k; i++) {
            res[i] = q.poll();
        }
        return res;
    }

}
