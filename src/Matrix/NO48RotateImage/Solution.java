package Matrix.NO48RotateImage;

public class Solution {
    //方法一 转置矩阵 + 左右翻转
    public void rotate(int[][] matrix) {
        int n = matrix.length;

        //transpose matrix 转置矩阵
        for (int i = 0; i < n; i++)
            for (int j = i + 1; j < n; j++) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = temp;
            }

        //reverse each row 翻转每一列
        for (int j = 0; j < n / 2; j++)
            for (int i = 0; i < n; i++) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[i][n - 1 - j];
                matrix[i][n - 1 - j] = temp;
            }

    }

    //方法二 旋转矩阵
    public void rotate2(int[][] matrix) {
        int n = matrix.length;
        //将方阵划分为等大小的矩阵 循环的是这个矩阵的所有元素
        //矩阵长为n/2+n%2 宽为n/2
        for (int i = 0; i < n / 2 + n % 2; i++)
            for (int j = 0; j < n / 2; j++) {
                //四个矩阵 每次变化每个矩阵中相同位置处元素
                int[] temp = new int[4];
                int row = i;
                int col = j;
                //将四个矩阵相同位置处元素放入temp
                for (int k = 0; k < 4; k++) {
                    temp[k] = matrix[row][col];
                    //矩阵上点旋转90度后的坐标变换规律
                    int x = row;
                    row = col;
                    //旋转后 行row等于之前的列col 列col等于矩阵大小n+1-之前行row（在1为起始坐标下）得到的也是以1为起始的列
                    col = n - 1 - x;
                }
                //交换四个元素位置
                //利用循环数组 用取余%实现
                for (int k = 0; k < 4; k++) {
                    matrix[row][col] = temp[(k + 3) % 4];
                    int x = row;
                    row = col;
                    col = n - 1 - x;
                }
            }
    }

}
