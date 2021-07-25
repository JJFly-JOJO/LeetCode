package WeekContest.SingleWeek.NO246;

/**
 * @author zzj
 * @version 1.0
 * @date 2021/6/20 11:12
 * @description
 */
public class SolutionIII {

    private int[][] direct = new int[][]{{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

    private boolean[][] isVisited;

    private int row;

    private int col;

    private boolean flag;

    public int countSubIslands(int[][] grid1, int[][] grid2) {
        int ans = 0;
        row = grid1.length;
        col = grid1[0].length;
        isVisited = new boolean[row][col];
        for (int r = 0; r < row; r++) {
            for (int c = 0; c < col; c++) {
                if (!isVisited[r][c] && grid2[r][c] == 1) {
                    flag = true;
                    backtracking(r, c, grid1, grid2);
                    if (flag) {
                        ans++;
                    }
                }
            }
        }
        return ans;
    }

    private void backtracking(int r, int c, int[][] grid1, int[][] grid2) {
        if (isVisited[r][c]) {
            return;
        }
        isVisited[r][c] = true;
        if (grid1[r][c] != grid2[r][c]) {
            flag = false;
        }
        for (int[] ints : direct) {
            int rr = r + ints[0];
            int cc = c + ints[1];
            if (canReach(rr, cc) && grid2[rr][cc] == 1) {
                backtracking(rr, cc, grid1, grid2);
            }
        }
    }

    private boolean canReach(int rr, int cc) {
        return rr >= 0 && rr < row && cc >= 0 && cc < col;
    }

}
