package StackPriorityQueue.NO218TheSkylineProblem;

import java.util.*;

/**
 * @author zzj
 * @version 1.0
 * @date 2020/12/31 14:45
 * @description ----------堆与平衡树AVL比较--------
 */
public class Solution {

    public static void main(String[] args) {
        TreeMap<Integer, Integer> pq = new TreeMap<>();
        pq.put(1, 2);
        pq.put(1, 3);
        pq.put(3, 2);
        pq.put(2, 2);
        System.out.println(pq.lastEntry().getKey());
    }

    public List<List<Integer>> getSkyline(int[][] buildings) {
        PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> o1[0] - o2[0] != 0 ? o1[0] - o2[0] : o2[1] - o1[1]);
        //技巧！--------------------使用正数负数来区分左端点和右端点
        for (int[] b : buildings) {
            pq.add(new int[]{b[0], b[2]});
            pq.add(new int[]{b[1], -b[2]});
        }
        List<List<Integer>> res = new ArrayList<>();
        //key=height value=count
        TreeMap<Integer, Integer> heights = new TreeMap<>();
        //最小高度0
        heights.put(0, 0);
        int[] last = new int[]{0, 0};
        while (!pq.isEmpty()) {
            int[] p = pq.poll();
            if (p[1] > 0) {
                //左端点 入map 同时记录下当前该height的count
                heights.put(p[1], heights.getOrDefault(p[1], 0) + 1);
            } else {
                int v = heights.get(-p[1]) - 1;
                if (v == 0) {
                    heights.remove(-p[1]);
                } else {
                    heights.put(-p[1], v);
                }
            }
            int max = heights.lastKey();
            if (last[1] != max) {
                //高度发生改变
                //当前改变的下标
                last[0] = p[0];
                last[1] = max;
                res.add(Arrays.asList(last[0], last[1]));
            }
        }
        return res;
    }

}
