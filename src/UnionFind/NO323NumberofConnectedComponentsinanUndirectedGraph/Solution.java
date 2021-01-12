package UnionFind.NO323NumberofConnectedComponentsinanUndirectedGraph;

import java.util.Arrays;

/**
 * @author zzj
 * @version 1.0
 * @date 2020/10/27 21:11
 * @description ----------按秩合并中 如果我们不关心树的实际高度 我们可以用节点数量粗略代替树高----------
 */
public class Solution {

    private int[] union;
    private int[] height;
    private int size;

    private int find(int x) {
        if (union[x] != x) {
            union[x] = find(union[x]);
        }
        return union[x];
    }

    private void unionF(int x, int y) {
        x = find(x);
        y = find(y);
        if (x == y) {
            return;
        }
        if (height[x] < height[y]) {
            int t = x;
            x = y;
            y = t;
        }
        union[y] = x;
        height[x] += height[y];
        size--;
    }

    public int countComponents(int n, int[][] edges) {
        //initialize union
        union = new int[n];
        for (int i = 0; i < n; i++) {
            union[i] = i;
        }
        height = new int[n];
        Arrays.fill(height, 1);
        size = n;
        //union
        for (int[] edge : edges) {
            unionF(edge[0], edge[1]);
        }
        return size;
    }

}
