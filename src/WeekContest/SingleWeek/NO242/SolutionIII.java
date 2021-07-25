package WeekContest.SingleWeek.NO242;

/**
 * @author zzj
 * @version 1.0
 * @date 2021/5/23 10:59 -------------动态规划+前缀和 前缀和伴随动态规划动态更新-------------
 * 5
 * 99998
 */
public class SolutionIII {

    public boolean canReach(String s, int minJump, int maxJump) {
        char[] chars = s.toCharArray();
        //前缀和记录着前面区间能够到达reach的个数
        int[] subSum = new int[chars.length + 1];
        int[] dp = new int[chars.length];
        //initialize
        dp[0] = 1;
        for (int i = 0; i <= minJump; i++) {
            subSum[i + 1] = 1;
        }
        for (int i = minJump; i < dp.length; i++) {
            if (chars[i] == '0') {
                int left = Math.max(0, i - maxJump);
                int right = i - minJump;
                int canReachCount = subSum[right + 1] - subSum[left];
                if (canReachCount > 0) {
                    dp[i] = 1;
                }
            }
            subSum[i + 1] = subSum[i] + dp[i];
        }
        return dp[dp.length - 1] == 1;
    }

}
