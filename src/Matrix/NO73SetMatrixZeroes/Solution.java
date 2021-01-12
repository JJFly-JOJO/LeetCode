package Matrix.NO73SetMatrixZeroes;

import java.util.HashSet;
import java.util.Set;

/**
 * @author zzj
 */
public class Solution {

    /**
     * 方法一：时间复杂度mxn 空间复杂度mxn
     * 用两个集合分别存零元素所在的行列 然后遍历时遇到当前行或者当前列 就置0
     *
     * @param matrix
     */
    public void setZeroes(int[][] matrix) {
        int row = matrix.length;
        int col = matrix[0].length;

        //存放行列集合
        Set<Integer> rowSet = new HashSet<>();
        Set<Integer> colSet = new HashSet<>();

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (matrix[i][j] == 0) {
                    rowSet.add(i);
                    colSet.add(j);
                }
            }
        }

        //遍历matrix 置0
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (rowSet.contains(i) || colSet.contains(j)) {
                    matrix[i][j] = 0;
                }
            }
        }

    }

    /**
     * 方法二：
     * 原地的意义在于利用当前矩阵记录信息
     * 当(i,j)处为0 则在(i,0)和(0,j)首部置零作为标记 循环标记结束后 只用检查第一行第一列
     * <p>
     * 特殊情况:
     * 第一行第一列的第一个元素如果是0 那么需要单独标记 因为置0时不能确定当前值是已经就是0还是置0时被变为0的
     *
     * @param matrix
     */
    public void setZeroes2(int[][] matrix) {
        //int flagOfFistelement = matrix[0][0];
        boolean flagRow = false;
        boolean flagCol = false;
        int row = matrix.length;
        int col = matrix[0].length;

        //遍历 标记为0的元素
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (matrix[i][j] == 0) {
                    matrix[i][0] = 0;
                    matrix[0][j] = 0;
                    if (i == 0) {
                        flagRow = true;
                    }
                    if (j == 0) {
                        flagCol = true;
                    }
                }
            }
        }

        //遍历第一行
        for (int i = 1; i < col; i++) {
            if (matrix[0][i] == 0) {
                for (int j = 1; j < row; j++) {
                    matrix[j][i] = 0;
                }
            }
        }

        //遍历第一列
        for (int i = 1; i < row; i++) {
            if (matrix[i][0] == 0) {
                for (int j = 1; j < col; j++) {
                    matrix[i][j] = 0;
                }
            }
        }

        if (flagRow) {
            for (int i = 1; i < col; i++) {
                matrix[0][i] = 0;
            }
        }
        if (flagCol) {
            for (int i = 1; i < row; i++) {
                matrix[i][0] = 0;
            }
        }
    }

}
