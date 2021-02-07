package WeekContest.DoubleWeek.NO44;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * @author zzj
 * @version 1.0
 * @date 2021/1/23 22:18
 * @description
 */
public class SolutionII {

    public static void main(String[] args) {
        System.out.println(new SolutionII().minimumTeachings(3, new int[][]{{2}, {1, 3}, {1, 2}, {3}},
                new int[][]{{1, 4}, {1, 2}, {3, 4}, {2, 3}}));
    }

    public int minimumTeachings(int n, int[][] languages, int[][] friendships) {
        List<int[]> cantTalk = new ArrayList<>();
        for (int[] f : friendships) {
            if (!canTalk(languages[f[0] - 1], languages[f[1] - 1])) {
                cantTalk.add(f);
            }
        }
        if (cantTalk.size() == 0) {
            return 0;
        }
        HashSet<Integer> user = new HashSet<>();
        for (int[] p : cantTalk) {
            user.add(p[0]);
            user.add(p[1]);
        }
        int res = Integer.MAX_VALUE;
        for (int i = 1; i <= n; i++) {
            int t = 0;
            for (Integer l : user) {
                if (!contains(languages[l - 1], i)) {
                    t++;
                }
            }
            res = Math.min(t, res);
        }
        return res;
    }

    private boolean contains(int[] language, int teach) {
        for (int l : language) {
            if (l == teach) {
                return true;
            }
        }
        return false;
    }

    private boolean canTalk(int[] p1, int[] p2) {
        for (int item : p1) {
            for (int value : p2) {
                if (item == value) {
                    return true;
                }
            }
        }
        return false;
    }

}
