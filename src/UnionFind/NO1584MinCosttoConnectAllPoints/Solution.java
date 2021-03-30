package UnionFind.NO1584MinCosttoConnectAllPoints;

import java.util.PriorityQueue;

/**
 * @author zzj
 * @version 1.0
 * @date 2021/1/20 15:58
 * @description ---------------最小生成树问题：连通图中找到遍历所有节点的最小路径的方法（也就是生成一颗树 树是不存在环的 环只会增加cost）-------------
 * --------- Kruskal 算法（算法本质依靠并查集的合并，只不过是将边值从小到大合并）---------
 */
public class Solution {

    private int[] union;
    private int size;

    public static void main(String[] args) {
        System.out.println(new Solution().minCostConnectPoints(new int[][]{{0, 0}, {1, 1}, {1, 0}, {-1, 1}}));
    }

    public int minCostConnectPoints(int[][] points) {
        union = new int[points.length];
        size = points.length;
        int cost = 0;
        for (int i = 0; i < union.length; i++) {
            union[i] = i;
        }
        //记录所有边长以及对应的两点
        //当然也可以全部存入 再排序
        PriorityQueue<int[]> q = new PriorityQueue<>((o1, o2) -> o1[0] - o2[0]);
        for (int i = 0; i < points.length; i++) {
            for (int j = i + 1; j < points.length; j++) {
                q.add(new int[]{countDistance(points[i], points[j]), i, j});
            }
        }
        //出队合并并查集
        while (size > 1) {
            int[] cur = q.poll();
            if (find(cur[1]) != find(cur[2])) {
                cost += cur[0];
                unionF(cur[1], cur[2]);
            }
        }
        return cost;
    }

    private void unionF(int x, int y) {
        int a = find(x);
        int b = find(y);
        if (a != b) {
            union[b] = a;
            size--;
        }
    }

    private int countDistance(int[] p1, int[] p2) {
        return Math.abs(p1[0] - p2[0]) + Math.abs(p1[1] - p2[1]);
    }

    private int find(int x) {
        if (union[x] != x) {
            union[x] = find(union[x]);
        }
        return union[x];
    }

}
