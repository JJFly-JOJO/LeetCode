package UnionFind.NO684RedundantConnection;

/**
 * @author zzj
 * @version 1.0
 * @date 2021/1/14 19:20
 * @description -------------并查集+暴力-------------
 * 思路：删除一条边 如果剩下的元素组成两个集合 那么就不能组成树，如果删除后任然是一个集合，那么返回res
 */
public class Solution {

    private int[] union;

    private int size;

    public int[] findRedundantConnection(int[][] edges) {
        //删除一条边形成树（只冗余一条边） 那么edges边数一定等于节点数
        size = edges.length;
        union = new int[size + 1];
        for (int i = edges.length - 1; i >= 0; i--) {
            resSet();
            for (int j = 0; j < edges.length; j++) {
                if (j != i) {
                    unionF(edges[j][0], edges[j][1]);
                }
            }
            if (size == 1) {
                return edges[i];
            }
        }
        return null;
    }

    private int find(int x) {
        if (union[x] != x) {
            union[x] = find(union[x]);
        }
        return union[x];
    }

    private void unionF(int x, int y) {
        int a = find(x);
        int b = find(y);
        if (a != b) {
            union[b] = a;
            size--;
        }
    }

    private void resSet() {
        size = union.length - 1;
        for (int i = 1; i <= size; i++) {
            union[i] = i;
        }
    }

}
