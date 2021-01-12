package DFSBFS.NO286WallsandGates;

/**
 * @author zzj
 * @version 1.0
 * @date 2020/10/30 15:24
 * @description -----------------DFS---------------
 */
public class Solution2 {

    private int row;
    private int col;

    public void wallsAndGates(int[][] rooms) {
        if ((row = rooms.length) == 0 || (col = rooms[0].length) == 0) {
            return;
        }
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                //门作为dfs的起始点
                if (rooms[i][j] == 0) {
                    //思考：如果使用isVisited带来的问题！！-------------------我们无法保证当前dfs路径下的每个点到
                    //门的距离最短，或许下一个分支的dfs会出现更短距离
                    //boolean[][] isVisited = new boolean[row][col];
                    dfs(i, j, 0, rooms);
                }
            }
        }
    }

    private void dfs(int r, int c, int d, int[][] rooms) {
        if (r < 0 || r >= row || c < 0 || c >= col || d > rooms[r][c]) {
            return;
        }
        //isVisited[r][c] = true;
        rooms[r][c] = d;
        dfs(r - 1, c, d + 1, rooms);
        dfs(r + 1, c, d + 1, rooms);
        dfs(r, c - 1, d + 1, rooms);
        dfs(r, c + 1, d + 1, rooms);
    }
}
