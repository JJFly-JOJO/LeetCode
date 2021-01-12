package StackPriorityQueue.NO313SuperUglyNumber;

import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;

/**
 * @author zzj
 * @version 1.0
 * @date 2020/12/24 11:25
 * @description
 */
public class SolutionII {

    public int nthSuperUglyNumber(int n, int[] primes) {
        //小顶堆 我们只需要找到最小值
        PriorityQueue<Integer> q = new PriorityQueue<>();
        //去重
        Set<Integer> isVisited = new HashSet<>();
        q.add(1);
        int cnt = 1;
        while (cnt < n) {
            //poll出最小数并不需要对isVisited进行remove操作 因为最小数只可能出现一次（乘任意非1正数只会变大）
            int c = q.poll();
            //最小的数乘因数才能得到最小的数
            for (int i : primes) {
                long v = (long) c * i;
                if (!isVisited.contains((int) v) && v < Integer.MAX_VALUE) {
                    q.add((int) v);
                    isVisited.add((int) v);
                }
            }
            cnt++;
        }
        return q.peek();
    }

}
