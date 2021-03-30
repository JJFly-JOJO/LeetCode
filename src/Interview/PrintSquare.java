package Interview;

/**
 * @author zzj
 * @version 1.0
 * @date 2021/2/26 0:04
 * @description
 */
public class PrintSquare {

    public static void main(String[] args) {
        new PrintSquare().printSquare(9);
    }

    public void printSquare(int n) {
        int edge = n - (n - 1) % 4;
        char[][] matrix = new char[edge][edge];
        int top = 0;
        int bottom = edge - 1;
        int left = 0;
        int right = edge - 1;
        for (int i = edge; i >= 1; i -= 4) {
            for (int j = left; j <= right; j++) {
                matrix[top][j] = '+';
                matrix[bottom][j] = '+';
            }
            for (int j = top + 1; j < bottom; j++) {
                matrix[j][left] = '+';
                matrix[j][right] = '+';
            }
            top += 2;
            bottom -= 2;
            left += 2;
            right -= 2;
        }
        for (int i = 0; i < edge; i++) {
            for (int j = 0; j < edge; j++) {
                System.out.print(matrix[i][j]);
            }
            System.out.println();
        }
    }
}
