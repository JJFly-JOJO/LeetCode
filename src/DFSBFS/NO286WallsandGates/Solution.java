package DFSBFS.NO286WallsandGates;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

/**
 * @author zzj
 * -------------------BFS---------------------
 */
public class Solution {

    private int[][] direction = new int[][]{{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

    public void wallsAndGates(int[][] rooms) {
        if (rooms.length == 0 || rooms[0].length == 0) {
            return;
        }
        List<int[]> doors = findDoors(rooms);
        for (int[] d : doors) {
            bfs(d, rooms);
        }
    }

    private void bfs(int[] door, int[][] rooms) {
        int row = rooms.length;
        int col = rooms[0].length;
        boolean[][] isVisited = new boolean[row][col];
        int[] begin = new int[]{door[0], door[1], 0};
        Queue<int[]> queue = new ArrayDeque<>();
        queue.add(begin);
        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            if (!isVisited[cur[0]][cur[1]] && cur[2] <= rooms[cur[0]][cur[1]]) {
                isVisited[cur[0]][cur[1]] = true;
                rooms[cur[0]][cur[1]] = cur[2];
                int nextVal = cur[2] + 1;
                for (int i = 0; i < 4; i++) {
                    int r = cur[0] + direction[i][0];
                    int c = cur[1] + direction[i][1];
                    if (r >= 0 && r < row && c >= 0 && c < col) {
                        queue.add(new int[]{r, c, nextVal});
                    }
                }
            }
        }
    }

    private List<int[]> findDoors(int[][] rooms) {
        int row = rooms.length;
        int col = rooms[0].length;
        List<int[]> doors = new ArrayList<>();
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (rooms[i][j] == 0) {
                    doors.add(new int[]{i, j});
                }
            }
        }
        return doors;
    }

}
