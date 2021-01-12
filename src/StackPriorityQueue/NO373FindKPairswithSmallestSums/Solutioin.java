package StackPriorityQueue.NO373FindKPairswithSmallestSums;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

/**
 * @author zzj
 * @version 1.0
 * @date 2020/12/25 10:27
 * @description --------------最大堆解法-------------
 * 思路：我们只维护容量为k的最大堆，遍历所有的组合，如果小于堆顶就加入到堆中，
 * 缺点：遍历所有组合 优势：只维护k规模的堆 插入效率为O(lgk)
 * 该题时间复杂度:O(nmlgk)  n为num1的规模 m为num2的规模
 */
public class Solutioin {

    public List<List<Integer>> kSmallestPairs(int[] nums1, int[] nums2, int k) {
        PriorityQueue<Integer[]> q = new PriorityQueue<>((o1, o2) -> (o2[0] + o2[1]) - (o1[0] + o1[1]));
        for (int item : nums1) {
            for (int value : nums2) {
                Integer[] it = new Integer[]{item, value};
                if (q.size() < k) {
                    q.add(it);
                } else {
                    if (it[0] + it[1] < q.peek()[0] + q.peek()[1]) {
                        q.poll();
                        q.add(it);
                    }
                }
            }
        }
        List<List<Integer>> res = new ArrayList<>();
        while (!q.isEmpty()) {
            res.add(Arrays.asList(q.poll()));
        }
        return res;
    }

}
