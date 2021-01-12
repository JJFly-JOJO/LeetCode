package Matrix.NO311SparseMatrixMultiplication;

/**
 * @author zzj
 * @version 1.0
 * @date 2020/11/5 14:03
 * @description
 */
public class Solution {

    public int[][] multiply(int[][] A, int[][] B) {
        int r = A.length;
        int m = A[0].length;
        int c = B[0].length;
        int[][] res = new int[r][c];
        //预处理数据 记录一行全为0 或者一列全为0的情况
        boolean[] aRow = new boolean[r];
        boolean[] bRow = new boolean[c];
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < m; j++) {
                if (A[i][j] != 0) {
                    aRow[i] = true;
                    break;
                }
            }
        }
        for (int i = 0; i < c; i++) {
            for (int j = 0; j < m; j++) {
                if (B[j][i] != 0) {
                    bRow[i] = true;
                    break;
                }
            }
        }
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                if (!aRow[i] || !bRow[j]) {
                    continue;
                }
                int v = 0;
                for (int k = 0; k < m; k++) {
                    v += A[i][k] * B[k][j];
                }
                res[i][j] = v;
            }
        }
        return res;
    }

}
