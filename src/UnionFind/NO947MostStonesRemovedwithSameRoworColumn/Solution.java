package UnionFind.NO947MostStonesRemovedwithSameRoworColumn;

/**
 * @author zzj
 * @version 1.0
 * @date 2021/1/18 0:12
 * @description
 */
public class Solution {

    private int[] union;

    private int size;

    public int removeStones(int[][] stones) {
        size = stones.length;
        union = new int[size];
        for (int i = 0; i < union.length; i++) {
            union[i] = i;
        }
        for (int i = 0; i < stones.length; i++) {
            for (int j = i + 1; j < stones.length; j++) {
                if (onRowCol(stones[i], stones[j])) {
                    unionF(i, j);
                }
            }
        }
        return stones.length - size;
    }

    private boolean onRowCol(int[] st1, int[] st2) {
        return st1[0] == st2[0] || st1[1] == st2[1];
    }

    private void unionF(int x, int y) {
        int a = find(x);
        int b = find(y);
        if (a != b) {
            union[b] = a;
            size--;
        }
    }

    private int find(int x) {
        if (union[x] != x) {
            union[x] = find(union[x]);
        }
        return union[x];
    }


}
