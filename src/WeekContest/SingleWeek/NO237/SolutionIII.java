package WeekContest.SingleWeek.NO237;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * @author zzj
 * @version 1.0
 * @date 2021/4/18 10:40
 * @description [6, 1, 2, 9, 4, 10, 0, 11, 5, 13, 3, 8, 12, 7]
 */
public class SolutionIII {

    public int[] getOrder(int[][] tasks) {
        Integer[] dict = new Integer[tasks.length];
        for (int i = 0; i < dict.length; i++) {
            dict[i] = i;
        }
        Arrays.sort(dict, (o1, o2) -> tasks[o1][0] - tasks[o2][0]);
        PriorityQueue<Integer> pq = new PriorityQueue<>((o1, o2) -> tasks[o1][1] - tasks[o2][1] == 0 ? o1 - o2 : tasks[o1][1] - tasks[o2][1]);
        int[] res = new int[tasks.length];
        int rIdx = 0;
        int tIdx = 0;
        int time = tasks[dict[0]][0];
        while (rIdx < res.length) {
            while (tIdx < dict.length && tasks[dict[tIdx]][0] <= time) {
                pq.add(dict[tIdx]);
                tIdx++;
            }
            if (!pq.isEmpty()) {
                int t = pq.poll();
                time += tasks[t][1];
                res[rIdx++] = t;
            } else if (tIdx < dict.length) {
                time = tasks[dict[tIdx]][0];
            }
        }
        return res;
    }

}
