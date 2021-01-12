package DynamicProgramming.NO174DungeonGame;

/**
 * @author zzj
 * @version 1.0
 * @date 2020/11/18 14:36
 * @description
 * 思考：我们不能从骑士处作为起点到达公主处的原因：我们要知道，do是不可能回退（也就是当前问题更新后是不能取改变前面
 * 子问题的值的），对于-4 -5 5 -3 -1 的情况我们就可以看到只要我们到达了5处就一定能到达终点，因此只需奥初始值为9+1
 * 即可，但是要确定我们的初始值却是要根据子问题的上层问题来确定 这是dp无法实现的  我们只能换思路----倒着来
 */
public class Solution {

    public int calculateMinimumHP(int[][] dungeon) {
        int row = dungeon.length;
        int col = dungeon[0].length;
        int[][] dp = new int[row][col];
        //initialize
        //dp[row - 1][col - 1] = dungeon[row - 1][col - 1] >= 0 ? 0 : dungeon[row - 1][col - 1];
        //--------------------代码优雅 用min替代三元运算符---------------------
        dp[row - 1][col - 1] = Math.min(dungeon[row - 1][col - 1], 0);
        //update row
        for (int i = col - 2; i >= 0; i--) {
            int v = dungeon[row - 1][i] + dp[row - 1][i + 1];
            dp[row - 1][i] = Math.min(v, 0);
        }
        //update col
        for (int i = row - 2; i >= 0; i--) {
            int v = dungeon[i][col - 1] + dp[i + 1][col - 1];
            dp[i][col - 1] = Math.min(v, 0);
        }
        //dp
        for (int r = row - 2; r >= 0; r--) {
            for (int c = col - 2; c >= 0; c--) {
                int v = dungeon[r][c] + Math.max(dp[r + 1][c], dp[r][c + 1]);
                dp[r][c] = Math.min(v, 0);
            }
        }
        return -dp[0][0] + 1;
    }

    /**
     * --------------------状态压缩--------------
     *
     * @param dungeon
     * @return
     */
    public int calculateMinimumHPII(int[][] dungeon) {
        int row = dungeon.length;
        int col = dungeon[0].length;
        int[] dp = new int[col];
        int[] dpCol = new int[row];
        //initialize
        //--------------------代码优雅 用min替代三元运算符---------------------
        dp[col - 1] = Math.min(dungeon[row - 1][col - 1], 0);
        dpCol[row - 1] = dp[col - 1];
        //update row
        for (int i = col - 2; i >= 0; i--) {
            int v = dungeon[row - 1][i] + dp[i + 1];
            dp[i] = Math.min(v, 0);
        }
        //update col
        for (int i = row - 2; i >= 0; i--) {
            int v = dungeon[i][col - 1] + dpCol[i + 1];
            dpCol[i] = Math.min(v, 0);
        }
        //dp
        for (int r = row - 2; r >= 0; r--) {
            dp[col - 1] = dpCol[r];
            for (int c = col - 2; c >= 0; c--) {
                int v = dungeon[r][c] + Math.max(dp[c], dp[c + 1]);
                dp[c] = Math.min(v, 0);
            }
        }
        return -dp[0] + 1;
    }

}
