package DFSBFS.NO417PacificAtlanticWaterFlow;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zzj
 * @version 1.0
 * @date 2021/6/7 11:18
 * @description
 */
public class Solution {

    public List<List<Integer>> pacificAtlantic(int[][] heights) {
        int[][] orientation = new int[][]{{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
        boolean[][] pacific = new boolean[heights.length][heights[0].length];
        boolean[][] atlantic = new boolean[heights.length][heights[0].length];
        //top
        for (int i = 0; i < heights[0].length; i++) {
            pacific[0][i] = true;
            for (int[] ints : orientation) {
                dfs(ints[0], i + ints[1], heights[0][i], pacific, heights, orientation);
            }
        }
        //left
        for (int i = 0; i < heights.length; i++) {
            pacific[i][0] = true;
            for (int[] ints : orientation) {
                dfs(i + ints[0], ints[1], heights[i][0], pacific, heights, orientation);
            }
        }
        //bottom
        int bottom = heights.length - 1;
        for (int i = 0; i < heights[0].length; i++) {
            atlantic[bottom][i] = true;
            for (int[] ints : orientation) {
                dfs(bottom + ints[0], i + ints[1], heights[bottom][i], atlantic, heights, orientation);
            }
        }
        //right
        int right = heights[0].length - 1;
        for (int i = 0; i < heights.length; i++) {
            atlantic[i][right] = true;
            for (int[] ints : orientation) {
                dfs(i + ints[0], right + ints[1], heights[i][right], atlantic, heights, orientation);
            }
        }
        List<List<Integer>> ans = new ArrayList<>();
        for (int i = 0; i < heights.length; i++) {
            for (int j = 0; j < heights[0].length; j++) {
                if (pacific[i][j] && atlantic[i][j]) {
                    List<Integer> pos = new ArrayList<>();
                    pos.add(i);
                    pos.add(j);
                    ans.add(pos);
                }
            }
        }
        return ans;
    }

    private void dfs(int r, int c, int lastHeight, boolean[][] area, int[][] heights, int[][] orienation) {
        if (!canReach(r, c, lastHeight, heights) || area[r][c]) {
            return;
        }
        area[r][c] = true;
        for (int[] ints : orienation) {
            dfs(r + ints[0], c + ints[1], heights[r][c], area, heights, orienation);
        }
    }

    private boolean canReach(int r, int c, int lastHeight, int[][] heights) {
        return r >= 0 && r < heights.length && c >= 0 && c < heights[0].length && lastHeight <= heights[r][c];
    }

}
