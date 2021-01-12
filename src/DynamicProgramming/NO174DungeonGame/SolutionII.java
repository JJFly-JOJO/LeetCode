package DynamicProgramming.NO174DungeonGame;

import java.util.Arrays;

/**
 * @author zzj
 * @version 1.0
 * @date 2020/11/20 9:52
 * @description -------------带有记忆的回溯方法----------
 * 思路：类似我们的动态规划，我们的上层问题的值只能靠递归的返回值来更新
 * （也就是递归到最小子问题，然后ret时进行问题迭代）本质与动态规划的思想一致
 */
public class SolutionII {

    private int[][] memo;
    private int row;
    private int col;

    public static void main(String[] args) {
        System.out.println(new SolutionII().calculateMinimumHP(new int[][]{{-2, -3, 3}, {-5, -10, 1}, {10, 30, -5}}));
    }

    public int calculateMinimumHP(int[][] dungeon) {
        row = dungeon.length;
        col = dungeon[0].length;
        memo = new int[row][col];
        for (int[] i : memo) {
            Arrays.fill(i, Integer.MIN_VALUE);
        }
        int res = dfs(0, 0, dungeon);
        return res >= 0 ? 1 : -res + 1;
    }

    private int dfs(int r, int c, int[][] dungeon) {
        if (r < 0 || r >= row || c < 0 || c >= col) {
            return Integer.MIN_VALUE;
        }
        if (r == row - 1 && c == col - 1) {
            return Math.min(dungeon[r][c], 0);
        }
        if (memo[r][c] != Integer.MIN_VALUE) {
            return memo[r][c];
        }
        int t = Math.max(dfs(r + 1, c, dungeon), dfs(r, c + 1, dungeon)) + dungeon[r][c];
        if (t > 0) {
            t = 0;
        }
        memo[r][c] = t;
        return t;
    }

}
