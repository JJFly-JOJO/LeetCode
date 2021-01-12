package WeekContest.SingleWeek.NO221;

/**
 * @author zzj
 * @version 1.0
 * @date 2020/12/29 11:17
 * @description
 */
public class SolutionIII {

    private int row;

    private int col;

    public int[] findBall(int[][] grid) {
        row = grid.length;
        col = grid[0].length;
        int[] ans = new int[col];
        for (int i = 0; i < col; i++) {
            ans[i] = dfs(0, i, grid);
        }
        return ans;
    }

    private int dfs(int r, int c, int[][] grid) {
        if (grid[r][c] == 1) {
            if (c == col - 1 || grid[r][c + 1] == -1) {
                return -1;
            }
        }
        if (grid[r][c] == -1) {
            if (c == 0 || grid[r][c - 1] == 1) {
                return -1;
            }
        }
        if (r == row - 1) {
            return grid[r][c] == 1 ? c + 1 : c - 1;
        }
        return grid[r][c] == 1 ? dfs(r + 1, c + 1, grid) : dfs(r + 1, c - 1, grid);
    }

}
