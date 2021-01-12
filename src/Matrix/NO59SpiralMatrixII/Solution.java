package Matrix.NO59SpiralMatrixII;

public class Solution {

    public static void main(String[] args) {
        int[][] matrix=new Solution().generateMatrix(3);
    }
    /**
     * 对比NO54 是使用矩阵对角进行判断终止条件
     * 此题使用矩形的四条边界值进行判断终止条件
     *
     * @param n
     * @return
     */
    public int[][] generateMatrix(int n) {
        int[][] matrix = new int[n][n];
        //top行
        int t = 0;
        //right列
        int r = n - 1;
        //bottom行
        int b = n - 1;
        //left列
        int l = 0;
        int num = 1;
        while (num <= n * n) {
            for (int i = l; i <= r; i++) {
                matrix[t][i] = num++;
            }
            t++;
            for (int i = t; i <= b; i++) {
                matrix[i][r] = num++;
            }
            r--;
            for (int i = r; i >= l; i--) {
                matrix[b][i] = num++;
            }
            b--;
            for (int i = b; i >= t; i--) {
                matrix[i][l] = num++;
            }
            l++;
        }
        return matrix;
    }

}
