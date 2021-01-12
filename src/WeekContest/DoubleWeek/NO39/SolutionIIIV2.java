package WeekContest.DoubleWeek.NO39;

import java.util.Arrays;

/**
 * @author zzj
 * @version 1.0
 * @date 2020/11/16 0:04
 * @description --------------带有记忆的回溯  注意dfs的顺序 步数长的先dfs 步数少的再dfs更新步数长的
 */
public class SolutionIIIV2 {

    private int[] remember;

    private int last = 6000;

    public int minimumJumps(int[] forbidden, int a, int b, int x) {
        /*if (a >= b) {
            last = x + a;
        } else {
            last = (b - a) * a;
        }*/
        remember = new int[last + 1];
        Arrays.fill(remember, 20000);
        //技巧！----------------------------------我们可以直接利用int[] remember 用-1来表示无法到达
        // 正好remember[pos] < step才会更新remember 由于步数不可能是负数 那么走到-1时是不会递归下去的
        //Set<Integer> f = new HashSet<>();
        for (int i : forbidden) {
            remember[i] = -1;
        }
        dfs(0, 0, a, b, false);
        return remember[x] >= 20000 ? -1 : remember[x];
    }

    private void dfs(int pos, int step, int a, int b, boolean tag) {
        if (pos < 0 || pos > last || remember[pos] <= step) {
            return;
        }
        remember[pos] = step;
        if (tag) {
            dfs(pos - b, step + 1, a, b, false);
        }
        dfs(pos + a, step + 1, a, b, true);
    }
}
