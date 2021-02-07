package UnionFind.NO1584MinCosttoConnectAllPoints;

import java.util.Arrays;

/**
 * @author zzj
 * @version 1.0
 * @date 2021/1/21 10:15
 * @description -----------prim算法----------
 */
public class SolutionII {

    public int minCostConnectPoints(int[][] points) {
        int cost = 0;
        int n = points.length;
        int[][] dist = new int[n][n];
        //计算所有可能边的距离
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                //---------------------------------注意此题不存在两个节点无法到达的情况 如果两个节点无法到达 一定要以无穷大表示
                dist[i][j] = dist[j][i] = countDistance(points[i], points[j]);
            }
        }
        boolean[] isVisited = new boolean[n];
        int[] d = new int[n];
        Arrays.fill(d, Integer.MAX_VALUE);
        //prim
        //加入n次
        for (int i = 0; i < n; i++) {
            int add = -1;
            //找到当前未加入的节点中到当前树最小距离的节点
            for (int j = 0; j < n; j++) {
                if (!isVisited[j] && (add == -1 || d[add] > d[j])) {
                    add = j;
                }
            }
            //找到节点 加入结果集
            if (d[add] != Integer.MAX_VALUE) {
                cost += d[add];
            }
            isVisited[add] = true;
            //更新到当前新加入节点的其他节点的距离
            for (int j = 0; j < n; j++) {
                d[j] = Math.min(d[j], dist[add][j]);
            }
        }
        return cost;
    }

    private int countDistance(int[] p1, int[] p2) {
        return Math.abs(p1[0] - p2[0]) + Math.abs(p1[1] - p2[1]);
    }
}
