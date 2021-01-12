package Backtracking.NO79WordSearch;

/**
 * @author zzj
 * @version 1.0
 * @date 2020/12/10 10:52
 * @description
 */
public class Solution {

    private int row;

    private int col;

    /**
     * -----------------优化空间-----------
     * 可以不需要isVisited来记录，我们可以利用board[][],将遍历过的board[][]中的元素置为' ',递归返回后再重置
     */
    private boolean[][] isVisited;

    /**
     * 上 下 左 右
     */
    private int[][] o = new int[][]{{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    public boolean exist(char[][] board, String word) {
        char[] w = word.toCharArray();
        if (w.length == 0) {
            return true;
        }
        row = board.length;
        col = board[0].length;
        isVisited = new boolean[row][col];
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (board[i][j] == w[0]) {
                    isVisited[i][j] = true;
                    for (int k = 0; k < 4; k++) {
                        if (backtracking(i + o[k][0], j + o[k][1], 1, w, board)) {
                            return true;
                        }
                    }
                    isVisited[i][j] = false;
                }
            }
        }
        return false;
    }

    private boolean backtracking(int r, int c, int l, char[] w, char[][] board) {
        if (l == w.length) {
            return true;
        }
        if (r < 0 || r >= row || c < 0 || c >= col || board[r][c] != w[l] || isVisited[r][c]) {
            return false;
        }
        isVisited[r][c] = true;
        for (int i = 0; i < 4; i++) {
            if (backtracking(r + o[i][0], c + o[i][1], l + 1, w, board)) {
                return true;
            }
        }
        isVisited[r][c] = false;
        return false;
    }

}
