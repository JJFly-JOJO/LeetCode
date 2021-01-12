package StackPriorityQueue.NO313SuperUglyNumber;

import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;

/**
 * @author zzj
 * @version 1.0
 * @date 2020/12/24 10:21
 * @description --------------带有剪枝的回溯 max是动态减小 达到剪枝目的--------------
 */
public class Solution {


    private PriorityQueue<Integer> q = new PriorityQueue<>((o1, o2) -> o2 - o1);
    private int limit = 0;
    private int max = Integer.MAX_VALUE;
    private Set<Integer> isVisited = new HashSet<>();

    public static void main(String[] args) {
        System.out.println(new Solution().nthSuperUglyNumber(12, new int[]{2, 7, 13, 19}));
    }

    public int nthSuperUglyNumber(int n, int[] primes) {
        limit = n;
        backtracking(1, primes);
        return q.peek();
    }

    private void backtracking(int cur, int[] primes) {
        if (cur > max || cur < 0 || isVisited.contains(cur)) {
            return;
        }
        if (q.size() < limit) {
            q.add(cur);
            isVisited.add(cur);
        } else {
            if (cur < q.peek()) {
                int v = q.poll();
                q.add(cur);
                isVisited.remove(v);
                isVisited.add(cur);
                max = Math.min(v, max);
            } else {
                max = Math.min(cur, max);
            }
        }
        for (int i : primes) {
            backtracking(cur * i, primes);
        }
    }

}
