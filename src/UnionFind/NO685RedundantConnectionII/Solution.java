package UnionFind.NO685RedundantConnectionII;

import java.util.Arrays;

/**
 * @author zzj
 * @version 1.0
 * @date 2021/1/15 16:11
 * @description
 */
public class Solution {

    public static void main(String[] args) {
        System.out.println(Arrays.toString(new Solution().findRedundantDirectedConnection(new int[][]{{4, 1}, {4, 5}, {2, 4}, {5, 3}, {2, 1}})));
    }

    public int[] findRedundantDirectedConnection(int[][] edges) {
        Union u = new Union(edges.length + 1);
        int[] parent = new int[edges.length + 1];
        for (int i = 0; i < parent.length; i++) {
            parent[i] = i;
        }
        int[] conflictEdge = null;
        int[] cycleEdge = null;
        for (int[] e : edges) {
            if (parent[e[1]] != e[1]) {
                //当前节点已经有入度 发生入度冲突
                conflictEdge = e;
            } else {
                parent[e[1]] = e[0];
                if (u.find(e[0]) != u.find(e[1])) {
                    u.unionF(e[0], e[1]);
                } else {
                    cycleEdge = e;
                }
            }
        }
        //judge
        if (conflictEdge != null) {
            return cycleEdge == null ? conflictEdge : new int[]{parent[conflictEdge[1]], conflictEdge[1]};
        }
        //没有入度冲突的边 说明当前情况为多余的一条边连接了树的根节点 导致所有节点的入度都为1 则直接返回成环的边
        return cycleEdge;
    }

    public class Union {

        private int[] u;

        public Union(int size) {
            u = new int[size];
            for (int i = 0; i < size; i++) {
                u[i] = i;
            }
        }

        private void unionF(int x, int y) {
            int a = find(x);
            int b = find(y);
            if (a != b) {
                u[b] = a;
            }
        }

        private int find(int x) {
            if (u[x] != x) {
                u[x] = find(u[x]);
            }
            return u[x];
        }

    }

}
