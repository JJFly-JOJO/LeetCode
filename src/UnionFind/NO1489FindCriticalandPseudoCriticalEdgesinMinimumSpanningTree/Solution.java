package UnionFind.NO1489FindCriticalandPseudoCriticalEdgesinMinimumSpanningTree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author zzj
 * @version 1.0
 * @date 2021/1/21 11:33
 * @description
 */
public class Solution {

    public static void main(String[] args) {
        /*System.out.println(new Solution().findCriticalAndPseudoCriticalEdges(5, new int[][]{{0, 1, 1}, {1, 2, 1}, {2, 3, 2},
                {0, 3, 2}, {0, 4, 3}, {3, 4, 3}, {1, 4, 6}}));*/
        StringBuilder sb=new StringBuilder("abc");
        String s=null;
        System.out.println(sb.reverse());
        System.out.println(sb);
    }

    public List<List<Integer>> findCriticalAndPseudoCriticalEdges(int n, int[][] edges) {
        Integer[] e = new Integer[edges.length];
        for (int i = 0; i < e.length; i++) {
            e[i] = i;
        }
        Arrays.sort(e, (o1, o2) -> edges[o1][2] - edges[o2][2]);
        //Kruscal算法计算最小生成树的值
        Union u = new Union(n);
        int value = 0;
        for (int i = 0; i < e.length && u.count > 1; i++) {
            if (u.unionF(edges[e[i]][0], edges[e[i]][1])) {
                value += edges[e[i]][2];
            }
        }
        List<Integer> critical = new ArrayList<>();
        List<Integer> uncritical = new ArrayList<>();
        List<List<Integer>> res = new ArrayList<>();
        res.add(critical);
        res.add(uncritical);
        //暴力枚举所有边 判断是关键边还是伪关键边
        for (int i = 0; i < e.length; i++) {
            Union union = new Union(n);
            int v = 0;
            //首先判断是否是关键边 不加入此边看能否形成最小生成树
            for (int j = 0; j < e.length && union.count > 1; j++) {
                if (i != j && union.unionF(edges[e[j]][0], edges[e[j]][1])) {
                    v += edges[e[j]][2];
                }
            }
            if (v != value) {
                res.get(0).add(e[i]);
                continue;
            }
            //如果不是关键边 那么判断是否是伪关键边
            // 伪关键边的判断是如果把当前边加入到集合 能否生成最小生成树
            union = new Union(n);
            union.unionF(edges[e[i]][0], edges[e[i]][1]);
            v = edges[e[i]][2];
            for (int j = 0; j < e.length && union.count > 1; j++) {
                if (i != j && union.unionF(edges[e[j]][0], edges[e[j]][1])) {
                    v += edges[e[j]][2];
                }
            }
            if (v == value) {
                res.get(1).add(e[i]);
            }
        }
        return res;
    }

    public class Union {

        private int[] union;

        private int count;

        public Union(int n) {
            count = n;
            union = new int[n];
            for (int i = 0; i < n; i++) {
                union[i] = i;
            }
        }

        private boolean unionF(int x, int y) {
            int a = find(x);
            int b = find(y);
            if (a != b) {
                union[b] = a;
                count--;
                return true;
            }
            return false;
        }

        private int find(int x) {
            if (union[x] != x) {
                union[x] = find(union[x]);
            }
            return union[x];
        }

    }

}
