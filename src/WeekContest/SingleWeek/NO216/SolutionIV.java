package WeekContest.SingleWeek.NO216;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * @author zzj
 * @version 1.0
 * @date 2020/11/22 11:34
 * @description
 */
public class SolutionIV {

    public static void main(String[] args) {
        System.out.println(new SolutionIV().minimumEffort(new int[][]{{1, 2}, {2, 4}, {4, 8}}));
    }

    public int minimumEffort(int[][] tasks) {
        for (int[] i : tasks) {
            i[0] = i[1] - i[0];
        }
        int len = tasks.length;
        PriorityQueue<int[]> queue = new PriorityQueue<>(len, (o1, o2) -> o2[0] - o1[0]);
        queue.addAll(Arrays.asList(tasks));
        int[] t = queue.poll();
        int cost = t[1];
        int rest = t[0];
        while (!queue.isEmpty()) {
            t = queue.poll();
            int v = t[1] - rest;
            if (v > 0) {
                cost += v;
                rest = t[0];
            } else {
                rest -= (t[1] - t[0]);
            }
        }
        return cost;
    }

}
