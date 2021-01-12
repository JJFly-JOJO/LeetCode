package UnionFind.NO261GraphValidTree;

import java.util.Arrays;

/**
 * @author zzj
 * @version 1.0
 * @date 2020/10/27 20:12
 * @description
 */
public class Solution {

    private int[] union;
    private int[] height;
    private int size;

    /**
     * 路径压缩
     * @param x
     * @return
     */
    private int uFind(int x) {
        if (x != union[x]) {
            union[x] = uFind(union[x]);
        }
        return union[x];
    }

    private boolean unionF(int x, int y) {
        x = uFind(x);
        y = uFind(y);
        if (x == y) {
            return false;
        }
        if (height[x] < height[y]) {
            int t = x;
            x = y;
            y = t;
        }
        union[y] = x;
        height[x] += height[y];
        size--;
        return true;
    }

    public boolean validTree(int n, int[][] edges) {
        union = new int[n];
        for (int i = 0; i < n; i++) {
            union[i] = i;
        }
        height = new int[n];
        Arrays.fill(height, 1);
        size = n;
        //合并
        for (int[] edge : edges) {
            if (!unionF(edge[0], edge[1])) {
                return false;
            }
        }
        return size == 1;
    }
}
