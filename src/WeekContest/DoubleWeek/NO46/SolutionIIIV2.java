package WeekContest.DoubleWeek.NO46;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author zzj
 * @version 1.0
 * @date 2021/2/21 0:32
 * @description -------------BFS------------
 */
public class SolutionIIIV2 {

    public int[][] highestPeak(int[][] isWater) {
        int row = isWater.length;
        int col = isWater[0].length;
        Queue<int[]> queue = new LinkedList<>();
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (isWater[i][j] == 1) {
                    queue.add(new int[]{i, j});
                    isWater[i][j] = 0;
                } else {
                    isWater[i][j] = -1;
                }
            }
        }
        int[][] direction = new int[][]{{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int[] pos = queue.poll();
                for (int[] ints : direction) {
                    int[] p = new int[]{pos[0] + ints[0], pos[1] + ints[1]};
                    if (p[0] >= 0 && p[0] < row && p[1] >= 0 && p[1] < col && isWater[p[0]][p[1]] == -1) {
                        isWater[p[0]][p[1]] = isWater[pos[0]][pos[1]] + 1;
                        queue.add(p);
                    }
                }
            }
        }
        return isWater;
    }
}
