package StackPriorityQueue.NO1705MaximumNumberofEatenApples;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * @author zzj
 * @version 1.0
 * @date 2020/12/28 10:33
 * @description ---------贪心算法+优先队列 先吃快要过期的---------
 */
public class Solution {

    public int eatenApples(int[] apples, int[] days) {
        PriorityQueue<int[]> p = new PriorityQueue<>(Comparator.comparingInt(o -> o[1]));
        int res = 0;
        int i = 0;
        while (!p.isEmpty() || i < apples.length) {
            while (!p.isEmpty() && p.peek()[1] <= i) {
                p.poll();
            }
            if (i < apples.length && apples[i] > 0) {
                p.add(new int[]{apples[i], i + days[i]});
            }
            if (!p.isEmpty()) {
                //速度慢的原因：多次的poll add操作造成不必要的移除加入 注意堆的add操作是从堆底加入 然后向上移动
                // 也就是add操作的时间复杂度是O(lgn) 而poll操作是从堆顶弹出 O（1）复杂度
                //解决：使用peek逻辑
                int[] c = p.peek();
                if (--c[0] == 0) {
                    p.poll();
                }
                res++;
                /*int[] c = p.poll();
                res++;
                if (--c[0] > 0) {
                    p.add(c);
                }*/
            }
            i++;
        }
        return res;
    }

}
