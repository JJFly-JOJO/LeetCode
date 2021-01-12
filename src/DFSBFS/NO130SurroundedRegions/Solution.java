package DFSBFS.NO130SurroundedRegions;

public class Solution {

    /**
     * DFS
     * 注意这道题区别NO200 找连通点的个数
     * 这里由于边界的O与在内部的O是不同的 内部O才被变为X
     * 因此我们将边界上的O与内部的O区别开来
     * <p>
     * 我们DFS是要找哪部分集合？
     * 逆向思维：我们不去正向找满足条件的O集合
     * 这里我们可以换一种思路 从边界的O（B）出发 DFS找到边界出发的集合 全部置为B
     * 再遍历矩阵 对O置X
     *
     * @param board
     */
    public void solve(char[][] board) {
        if (board == null || board.length == 0) {
            return;
        }
        int row = board.length;
        int col = board[0].length;

        //遍历上下边界
        for (int i = 0; i < col; i++) {
            DFS(board, 0, i);
            DFS(board, row - 1, i);
        }

        //遍历左右边界
        for (int i = 1; i < row - 1; i++) {
            DFS(board, i, 0);
            DFS(board, i, col - 1);
        }

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (board[i][j] == 'O') {
                    board[i][j] = 'X';
                } else if (board[i][j] == 'B') {
                    board[i][j] = 'O';
                }
            }
        }

    }

    /**
     * @param board
     * @param r
     * @param c
     */
    private void DFS(char[][] board, int r, int c) {
        if (board[r][c] != 'O') {
            return;
        }
        int row = board.length;
        int col = board[0].length;
        board[r][c] = 'B';
        if (r - 1 >= 0) {
            DFS(board, r - 1, c);
        }
        if (r + 1 < row) {
            DFS(board, r + 1, c);
        }
        if (c - 1 >= 0) {
            DFS(board, r, c - 1);
        }
        if (c + 1 < col) {
            DFS(board, r, c + 1);
        }
    }

}
