package String.NO131PalindromePartitioning;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zzj
 * @version 1.0
 * @date 2021/1/20 10:36
 * @description -----------------使用动态规划预处理判断是否是回文串------------------
 */
public class SolutionII {

    private boolean[][] dp;

    public List<List<String>> partition(String s) {
        char[] chars = s.toCharArray();
        dp = new boolean[chars.length][chars.length];
        //动态规划预处理判断
        for (int r = 0; r < chars.length; r++) {
            for (int l = 0; l <= r; l++) {
                if (chars[r] == chars[l] && (r <= l + 2 || dp[l + 1][r - 1])) {
                    dp[l][r] = true;
                }
            }
        }
        List<List<String>> res = new ArrayList<>();
        backtracking(0, chars, new ArrayList<>(), res);
        return res;
    }

    private void backtracking(int start, char[] chars, List<String> path, List<List<String>> res) {
        if (start >= chars.length) {
            res.add(new ArrayList<>(path));
            return;
        }
        for (int i = start; i < chars.length; i++) {
            if (dp[start][i]) {
                path.add(new String(chars, start, i - start + 1));
                backtracking(i + 1, chars, path, res);
                path.remove(path.size() - 1);
            }
        }
    }
}
