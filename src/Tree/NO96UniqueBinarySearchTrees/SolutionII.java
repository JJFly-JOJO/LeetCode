package Tree.NO96UniqueBinarySearchTrees;

/**
 * @author zzj
 * @version 1.0
 * @date 2021/2/1 23:21
 * @description --------动态规划解法-------
 */
public class SolutionII {

    public static void main(String[] args) {
        System.out.println(new SolutionII().numTrees(2));
    }

    public int numTrees(int n) {
        int[][] dp = new int[n + 2][n + 2];
        //initialize
        for (int i = 1; i <= n; i++) {
            dp[i][i] = 1;
            dp[i][i - 1] = 1;
            dp[i + 1][i] = 1;
        }
        for (int i = 2; i <= n; i++) {
            for (int j = 1; j <= n - i + 1; j++) {
                int sum = 0;
                for (int k = 1; k < j + i; k++) {
                    sum += dp[j][k - 1] * dp[k + 1][j + i - 1];
                }
                dp[j][j + i - 1] = sum;
            }
        }
        return dp[1][n];
    }
}
