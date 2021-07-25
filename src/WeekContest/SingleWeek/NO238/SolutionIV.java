package WeekContest.SingleWeek.NO238;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * @author zzj
 * @version 1.0
 * @date 2021/4/25 11:14
 * @description
 */
public class SolutionIV {

    public static void main(String[] args) {
        ArrayList<Integer> list=new ArrayList<>();
        System.out.println(new SolutionIV().maxBuilding(5, new int[][]{{2, 1}, {4, 1}}));
    }

    public int maxBuilding(int n, int[][] restrictions) {
        int res = 0;
        boolean[] build = new boolean[n + 1];
        build[1] = true;
        PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> o1[1] - o2[1]);
        pq.add(new int[]{1, 0});
        pq.addAll(Arrays.asList(restrictions));
        while (!pq.isEmpty()) {
            int[] e = pq.poll();
            int pre = e[0] - 1;
            int next = e[0] + 1;
            int val = e[1] + 1;
            if (pre > 0 && !build[pre]) {
                build[pre] = true;
                pq.add(new int[]{pre, val});
                res = Math.max(val, res);
            }
            if (next < build.length && !build[next]) {
                build[next] = true;
                pq.add(new int[]{next, val});
                res = Math.max(val, res);
            }
            if (!build[e[0]]) {
                build[e[0]] = true;
            }
        }
        return res;
    }

}
