package Matrix.NO36ValidSudoku;

/**
 * @author zzj
 * @version 1.0
 * @date 2020/12/7 16:05
 * @description
 */
public class Solution {

    public boolean isValidSudoku(char[][] board) {
        boolean[][] r = new boolean[9][10];
        boolean[][] c = new boolean[9][10];
        boolean[][][] m = new boolean[3][3][10];
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (board[i][j] == '.') {
                    continue;
                }
                if (r[i][board[i][j] - '0']) {
                    return false;
                }
                r[i][board[i][j] - '0'] = true;
                if (c[j][board[i][j] - '0']) {
                    return false;
                }
                c[j][board[i][j] - '0'] = true;
                if (m[i / 3][j / 3][board[i][j] - '0']) {
                    return false;
                }
                m[i / 3][j / 3][board[i][j] - '0'] = true;
            }
        }
        return true;
    }

}
