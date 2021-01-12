package WeekContest.SingleWeek.NO213;

/**
 * @author zzj
 * @version 1.0
 * @date 2020/11/1 10:29
 * @description
 */
public class Solution2 {

    private int count = 0;

    public int countVowelStrings(int n) {
        for (int i = 0; i < 5; i++) {
            dfs(i, n - 1);
        }
        return count;
    }

    private void dfs(int cur, int n) {
        if (n == 0) {
            count++;
            return;
        }
        for (int i = cur; i < 5; i++) {
            dfs(i, n - 1);
        }
    }

}
