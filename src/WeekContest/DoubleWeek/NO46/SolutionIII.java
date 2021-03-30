package WeekContest.DoubleWeek.NO46;

/**
 * @author zzj
 * @version 1.0
 * @date 2021/2/20 23:20
 * @description
 */
public class SolutionIII {

    public int[][] highestPeak(int[][] isWater) {
        int row = isWater.length;
        int col = isWater[0].length;
        int sum = row * col;
        for (int r = 0; r < row; r++) {
            for (int c = 0; c < col; c++) {
                if (isWater[r][c] == 1) {
                    sum--;
                    isWater[r][c] = 0;
                } else {
                    isWater[r][c] = -1;
                }
            }
        }
        int hight = 0;
        while (sum > 0) {
            for (int r = 0; r < row; r++) {
                for (int c = 0; c < col; c++) {
                    if (isWater[r][c] == hight) {
                        if (r - 1 >= 0 && isWater[r - 1][c] == -1) {
                            isWater[r - 1][c] = hight + 1;
                            sum--;
                        }
                        if (r + 1 < row && isWater[r + 1][c] == -1) {
                            isWater[r + 1][c] = hight + 1;
                            sum--;
                        }
                        if (c - 1 >= 0 && isWater[r][c - 1] == -1) {
                            isWater[r][c - 1] = hight + 1;
                            sum--;
                        }
                        if (c + 1 < col && isWater[r][c + 1] == -1) {
                            isWater[r][c + 1] = hight + 1;
                            sum--;
                        }
                    }
                }
            }
            hight++;
        }
        return isWater;
    }

}
