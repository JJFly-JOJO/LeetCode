package WeekContest.SingleWeek.NO214;

import java.util.*;

/**
 * @author zzj
 * @version 1.0
 * @date 2020/11/8 11:16
 * @description
 */
public class Solution3 {

    public static void main(String[] args) {
        System.out.println(new Solution3().maxProfit(new int[]{773160767}, 252264991));
        System.out.println(Integer.MAX_VALUE);
        System.out.println(Long.MAX_VALUE);
    }

    public int maxProfit(int[] inventory, int orders) {
        List<Integer> list = new ArrayList<>();
        for (int i : inventory) {
            list.add(i);
        }
        long max = (long) 1e9 + 7;
        Collections.sort(list, ((o1, o2) -> o2 - o1));
        Queue<Integer> queue = new ArrayDeque<>(list);
        //哨兵
        queue.add(0);
        long res = 0L;
        int[] pollMax = new int[2];
        pollMax[0] = queue.poll();
        pollMax[1] = 1;
        while (orders > 0) {
            while (pollMax[0] == queue.peek()) {
                pollMax[1]++;
                queue.poll();
            }
            long diff = pollMax[0] - queue.peek();
            long p = orders / pollMax[1];
            if (p == 0) {
                res += orders * pollMax[0];
                break;
            } else if (p > diff) {
                long add = sum(pollMax[0], queue.peek());
                res += add * pollMax[1];
                pollMax[0] = queue.peek();
                orders -= pollMax[1] * diff;
            } else {
                //long add = (pollMax[0] - p + 1) * p + p * (p - 1) / 2;
                long add = sum(pollMax[0], (int) (pollMax[0] - p));
                res += add * pollMax[1];
                pollMax[0] -= p;
                orders = orders % pollMax[1];
            }
        }
        return (int) (res % max);
    }



    public long sum(int max, int min) {
        long n = max - min;
        long s = n * (min + 1) + n * (n - 1) / 2;
        return s;
    }

}
