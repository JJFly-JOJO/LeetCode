package WeekContest.SingleWeek.NO212;

/**
 * @author zzj
 * @version 1.0
 * @date 2020/10/25 10:55
 * @description ------------------------暴力dfs 存在超时 仅仅靠最优解来剪枝不够-------------------
 */
public class Solution3 {

    private int min = -1;

    private int row;

    private int col;

    private boolean[][] isVisited;

    public int minimumEffortPath(int[][] heights) {
        row = heights.length;
        col = heights[0].length;
        if (row == 1 && col == 1) {
            return 0;
        }
        //找到一条路径
        for (int i = 1; i < col; i++) {
            min = Math.max(Math.abs(heights[0][i] - heights[0][i - 1]), min);
        }
        for (int i = 1; i < row; i++) {
            min = Math.max(Math.abs(heights[i][col - 1] - heights[i - 1][col - 1]), min);
        }
        //DFS
        isVisited = new boolean[row][col];
        isVisited[0][0] = true;
        if (1 < col) {
            dfs(0, 1, heights, -1, heights[0][0]);
        }
        if (1 < row) {
            dfs(1, 0, heights, -1, heights[0][0]);
        }
        return min;
    }

    private void dfs(int r, int c, int[][] heights, int tempMin, int lastVal) {
        if (isVisited[r][c]) {
            return;
        }
        int diff = Math.abs(heights[r][c] - lastVal);
        if (diff > min) {
            return;
        }
        tempMin = Math.max(tempMin, diff);
        if (r == row - 1 && c == col - 1) {
            min = tempMin;
            return;
        }
        isVisited[r][c]=true;
        int rd=r+1;
        int ru=r-1;
        int cl=c-1;
        int cr=c+1;
        //上
        if(rd<row){
            dfs(rd,c,heights,tempMin,heights[r][c]);
        }
        if(ru>=0){
            dfs(ru,c,heights,tempMin,heights[r][c]);
        }
        if(cl>=0){
            dfs(r,cl,heights,tempMin,heights[r][c]);
        }
        if(cr<col){
            dfs(r,cr,heights,tempMin,heights[r][c]);
        }
        //isVisited[r][c]=false;
    }
}
