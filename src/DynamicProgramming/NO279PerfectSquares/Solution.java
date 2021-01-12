package DynamicProgramming.NO279PerfectSquares;

public class Solution {

    public int numSquares(int n) {
        if (n < 4) {
            return n;
        }
        //dp[0]我们用不到 但是到dp[n]是有n+1个元素
        int[] dp = new int[n + 1];
        //initialize
        dp[1] = 1;
        dp[2] = 2;
        dp[3] = 3;
        dp[4] = 1;
        int squareNum = 3;
        for (int i = 5; i <= n; i++) {
            if (i == squareNum * squareNum) {
                dp[i] = 1;
                squareNum++;
                continue;
            } else {
                int half = i / 2;
                //第一个枚举值要初始化
                dp[i] = dp[1] + dp[i - 1];
                for (int k = 2; k <= half; k++) {
                    dp[i] = Math.min(dp[i], dp[k] + dp[i - k]);
                }
            }
        }
        return dp[n];
    }

    /**
     * 区别上一种动态规划 我们当前层如果为m 则我们要遍历m/2次
     * 而这一种动态方法 由于j是平方增长 j越大 跳过的当前层的元素就越多 速度更快
     * 证明可行性；
     *      dp[i]=dp[j]+dp[i-j]（1）
     *      dp[i]=dp[j]+1   j=i-k*k （2）
     *      由于dp[]>=1 因此（1）会有很多没有意义的计算
     *      (1)会有很多重复的组合 比如dp[1] dp[3]和dp[2] dp[2]的组合是重复的
     *     （2）式 一个数必然是由完全平方数组合而成 而这组完全平方数必然有一个最大的完全平发数 以这个最大完全平方数作为枚举值！！
     * @param n
     * @return
     */
    public int numSquares2(int n) {
        if (n < 4) {
            return n;
        }
        int[] dp = new int[n + 1];
        //initialize
        dp[0] = 0;
        dp[1] = 1;
        dp[2] = 2;
        dp[3] = 3;
        dp[4] = 1;
        for (int i = 5; i <= n; i++) {
            //初始化dp[i] 由于dp[i]要找最小值 因此可以设置一个很大的值
            //我们可以设置i
            dp[i] = i;
            int temp;
            //i=16 j=4 则有dp[16]=dp[0]+1=0+1=1
            for (int j = 2; (temp = i - j * j) >= 0; j++) {
                dp[i] = Math.min(dp[i], dp[temp] + 1);
            }
        }
        return dp[n];
    }

}
