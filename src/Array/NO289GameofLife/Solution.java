package Array.NO289GameofLife;

/**
 * @author zzj
 * @version 1.0
 * @date 2020/10/13 16:29
 * 输入：
 * [
 *   [0,1,0],
 *   [0,0,1],
 *   [1,1,1],
 *   [0,0,0]
 * ]
 * 输出：
 * [
 *   [0,0,0],
 *   [1,0,1],
 *   [0,1,1],
 *   [0,1,0]
 * ]
 */
public class Solution {
    public void gameOfLife(int[][] board) {
        int row = board.length;
        int col = board[0].length;
        //3表示1->0 4表示0->1
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                int liveCeils = getAroundCeils(i, j, board, row, col);
                if (board[i][j] == 1) {
                    if (liveCeils < 2 || liveCeils > 3) {
                        board[i][j] = 3;
                    }
                } else {
                    if (liveCeils == 3) {
                        board[i][j] = 4;
                    }
                }
            }
        }
        update(board, row, col);
    }

    private void update(int[][] board, int row, int col) {
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (board[i][j] == 3) {
                    board[i][j] = 0;
                } else if (board[i][j] == 4) {
                    board[i][j] = 1;
                }
            }
        }
    }

    private int getAroundCeils(int i, int j, int[][] board, int row, int col) {
        int count = 0;
        //左上 上 右上 左中 右中 左下 下 右下
        //int[][] position=new int[][]{{i-1,j-1},{}}
        if (i - 1 >= 0 && j - 1 >= 0) {
            if (board[i - 1][j - 1] == 1 || board[i - 1][j - 1] == 3) {
                count++;
            }
        }
        if (i - 1 >= 0) {
            if (board[i - 1][j] == 1 || board[i - 1][j] == 3) {
                count++;
            }
        }
        if (i - 1 >= 0 && j + 1 < col) {
            if (board[i - 1][j + 1] == 1 || board[i - 1][j + 1] == 3) {
                count++;
            }
        }
        if (j - 1 >= 0) {
            if (board[i][j - 1] == 1 || board[i][j - 1] == 3) {
                count++;
            }
        }
        if (j + 1 < col) {
            if (board[i][j + 1] == 1 || board[i][j + 1] == 3) {
                count++;
            }
        }
        if (i + 1 < row && j - 1 >= 0) {
            if (board[i + 1][j - 1] == 1 || board[i + 1][j - 1] == 3) {
                count++;
            }
        }
        if (i + 1 < row) {
            if (board[i + 1][j] == 1 || board[i + 1][j] == 3) {
                count++;
            }
        }
        if (i + 1 < row && j + 1 < col) {
            if (board[i + 1][j + 1] == 1 || board[i + 1][j + 1] == 3) {
                count++;
            }
        }
        return count;
    }
}
