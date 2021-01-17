package UnionFind.NO684RedundantConnection;

/**
 * @author zzj
 * @version 1.0
 * @date 2021/1/15 10:30
 * @description -------------并查集------------
 * 思路：直接删除冗余连接，当两个元素属于同一个根，那么就形成了环，直接删除
 */
public class SolutionII {

    private int[] union;

    public int[] findRedundantConnection(int[][] edges) {
        union = new int[edges.length + 1];
        for (int i = 1; i <= edges.length; i++) {
            union[i] = i;
        }
        for (int[] edge : edges) {
            if (find(edge[0]) != find(edge[1])) {
                unionF(edge[0], edge[1]);
            } else {
                return edge;
            }
        }
        return null;
    }

    private void unionF(int x, int y) {
        int a = find(x);
        int b = find(y);
        if (a != b) {
            union[b] = a;
        }
    }

    private int find(int x) {
        if (union[x] != x) {
            union[x] = find(union[x]);
        }
        return union[x];
    }

}
