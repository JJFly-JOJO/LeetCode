package WeekContest.SingleWeek.NO225;

import java.util.PriorityQueue;

/**
 * @author zzj
 * @version 1.0
 * @date 2021/1/28 0:18
 * @description
 */
public class SolutionIII {

    public int kthLargestValue(int[][] matrix, int k) {
        //小顶堆
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        int row = matrix.length;
        int col = matrix[0].length;
        //可以优化 不需要重新申请dp空间 使用原来的matrix即可
        //int[][] dp = new int[row][col];
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (i == 0) {
                    if (j != 0) {
                        matrix[i][j] = matrix[i][j] ^ matrix[i][j - 1];
                    }
                } else if (j == 0) {
                    matrix[i][j] = matrix[i][j] ^ matrix[i - 1][j];
                } else {
                    matrix[i][j] = matrix[i][j] ^ matrix[i - 1][j - 1] ^ matrix[i - 1][j] ^ matrix[i][j - 1];
                }
                if (pq.size() < k) {
                    pq.add(matrix[i][j]);
                } else if (matrix[i][j] > pq.peek()) {
                    pq.poll();
                    pq.add(matrix[i][j]);
                }
            }
        }
        return pq.peek();
    }

}
