package WeekContest.SingleWeek.NO247;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zzj
 * @version 1.0
 * @date 2021/6/27 10:39
 * @description
 */
public class SolutionII {

    public static void main(String[] args) {
        int[][] grid=new SolutionII().rotateGrid(new int[][]{{1,2},{3,4}}, 1);
    }

    public int[][] rotateGrid(int[][] grid, int k) {
        int row = grid.length;
        int col = grid[0].length;
        int[][] ans = new int[row][col];
        int top = 0;
        int bottom = row - 1;
        int left = 0;
        int right = col - 1;
        List<Integer> mem = new ArrayList<>();
        while (top < bottom && left < right) {
            for (int i = left; i <= right; i++) {
                mem.add(grid[top][i]);
            }
            for (int i = top + 1; i < bottom; i++) {
                mem.add(grid[i][right]);
            }
            for (int i = right; i >= left; i--) {
                mem.add(grid[bottom][i]);
            }
            for (int i = bottom - 1; i > top; i--) {
                mem.add(grid[i][left]);
            }
            int idx = k;
            int len = mem.size();
            for (int i = left; i <= right; i++) {
                ans[top][i] = mem.get(idx++ % len);
            }
            for (int i = top + 1; i < bottom; i++) {
                ans[i][right] = mem.get(idx++ % len);
            }
            for (int i = right; i >= left; i--) {
                ans[bottom][i] = mem.get(idx++ % len);
            }
            for (int i = bottom - 1; i > top; i--) {
                ans[i][left] = mem.get(idx++ % len);
            }
            top++;
            bottom--;
            left++;
            right--;
            mem.clear();
        }
        return ans;
    }
}
