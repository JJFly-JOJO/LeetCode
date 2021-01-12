package WeekContest.SingleWeek.NO212;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author zzj
 * @version 1.0
 * @date 2020/10/26 16:04
 * @description -----------------方法一 二分搜索+BFS/DFS----------------
 * 分析：该题将方格抽象为点，两点之间有权值边（权值为两点差值绝对值），目标是找到从左上角到右下角的最短距离
 * 该题抽象为有权无向图中求两个源之间的最短路径
 * <p>
 * 注意一个关键点：当前节点只会与邻近节点之间存在关系 相隔节点是不会互相影响的
 * <p>
 * 思路：这里我们二分搜索是对最短路径搜索，利用二分搜索找到的mid值来规划BFS/DFS的搜索方向
 * <p>
 * 思考：类似Dijkstra 算法，如果我们使用BFS时，当前点1与下一个点3相连，点2还与点3相连，如果我们选择当前点1与点相连，而不选择点2与点3相连，
 * 是否会存在1与点3相连后，之后周围的点与这条路径都没有满足我们限制的路径长度mid，但是点2与点3相连后却有满足条件的路径mid呢？？
 * 答：首先我们要知道，我们不可能走回头路，也就是不存在点2到点3，然后点3回到点2，又存在路径的情况，也就是说当前点3与接下来的领接点没有路径，那么从点2到
 * 点3的路径自然是没有的。
 */
public class Solution3V1 {

    /**
     * 初始化四个方向 r c
     * up down left right
     */
    private int[][] direction = new int[][]{{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    /**
     * this is for dfs
     */
    private int row;
    private int col;

    public static void main(String[] args) {
        //int[][] height = new int[][]{{4, 3, 4, 10, 5, 5, 9, 2}, {10, 8, 2, 10, 9, 7, 5, 6}, {5, 8, 10, 10, 10, 7, 4, 2}, {5, 1, 3, 1, 1, 3, 1, 9}, {6, 4, 10, 6, 10, 9, 4, 6}};
        int[][] height = new int[][]{{1, 2, 1, 1, 1}, {1, 2, 1, 2, 1}, {1, 2, 1, 2, 1}, {1, 2, 1, 2, 1}, {1, 1, 1, 2, 1}};

        System.out.println(new Solution3V1().minimumEffortPathForDFS(height));
    }

    /**
     * ---------------------------二分搜索+BFS------------------
     *
     * @param heights
     * @return
     */
    public int minimumEffortPathForBFS(int[][] heights) {
        int row = heights.length;
        int col = heights[0].length;
        int left = 0;
        int right = (int) 1e6;
        int last = row * col - 1;
        int[] startN = new int[]{0, 0};
        Queue<int[]> queueForBfs = new LinkedList<>();
        //外层二分查找
        while (left < right) {
            int mid = (left + right) >>> 1;
            //ready for bfs
            boolean[] isVisited = new boolean[last + 1];
            queueForBfs.add(startN);
            isVisited[0] = true;
            //BFS
            while (!queueForBfs.isEmpty()) {
                int[] cur = queueForBfs.poll();
                for (int i = 0; i < 4; i++) {
                    int[] next = new int[]{cur[0] + direction[i][0], cur[1] + direction[i][1]};
                    int index = 0;
                    //易错点！！！！！------------------------------一行有多少个元素是由col表示
                    if (next[0] >= 0 && next[0] < row
                            && next[1] >= 0 && next[1] < col
                            && !isVisited[(index = next[0] * col + next[1])]
                            && Math.abs(heights[cur[0]][cur[1]] - heights[next[0]][next[1]]) <= mid) {
                        queueForBfs.add(next);
                        isVisited[index] = true;
                    }
                }
            }

            //二分查找 更新left right
            if (isVisited[last]) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }

    /**
     * ---------------------二分查找+DFS----------------
     *
     * @param heights
     * @return
     */
    public int minimumEffortPathForDFS(int[][] heights) {
        row = heights.length;
        col = heights[0].length;
        int left = 0;
        int right = (int) 1e6;
        int last = row * col - 1;
        while (left < right) {
            int mid = (left + right) >>> 1;
            boolean[] isVisited = new boolean[last + 1];
            if (recursive(0, 0, heights, isVisited, heights[0][0], mid)) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }

    private boolean recursive(int r, int c, int[][] heights, boolean[] isVisited, int lastValue, int mid) {
        int curPos = r * col + c;
        if (isVisited[curPos] || Math.abs(heights[r][c] - lastValue) > mid) {
            return false;
        }
        if (r == row - 1 && c == col - 1) {
            return true;
        }
        isVisited[curPos] = true;
        for (int i = 0; i < 4; i++) {
            int nR = r + direction[i][0];
            int nC = c + direction[i][1];
            if (nR >= 0 && nR < row && nC >= 0 && nC < col
                    && recursive(nR, nC, heights, isVisited, heights[r][c], mid)) {
                return true;
            }

        }
        //易错！！---------------------------这里我们要区别传统的dfs，类比BFS的isVisited，同样的道理，
        // 我们并不关注能满足mid条件下的所有可能到达的路径情况，我们只要能走到终点就停止，因此isVisited遍历过
        // 就不需要再重置为false，同样的，如果当前点到周围的点都无法满足mid，那同样是不可能存在其他点连接当前点后就存在路径的情况，
        //因此isVisited也不需要重置！！
        //isVisited[curPos] = false;
        return false;
    }

}
