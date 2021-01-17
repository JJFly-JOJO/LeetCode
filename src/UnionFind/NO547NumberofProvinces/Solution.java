package UnionFind.NO547NumberofProvinces;

/**
 * @author zzj
 * @version 1.0
 * @date 2021/1/14 16:01
 * @description
 */
public class Solution {

    private int[] union;
    private int size;

    public static void main(String[] args) {
        System.out.println(new Solution().findCircleNum(new int[][]{{1, 1, 1}, {1, 1, 1}, {1, 1, 1}}));
    }

    public int findCircleNum(int[][] isConnected) {
        union = new int[isConnected.length];
        for (int i = 0; i < union.length; i++) {
            union[i] = i;
        }
        size = isConnected.length;
        for (int i = 0; i < isConnected.length; i++) {
            for (int j = i + 1; j < isConnected.length; j++) {
                if (isConnected[i][j] == 1) {
                    unionF(i, j);
                }
            }
        }
        return size;
    }

    /**
     * 搜索过程中 路径优化
     */
    private int find(int x) {
        if (union[x] != x) {
            union[x] = find(union[x]);
        }
        //注意是返回union[x] 不是x union[x]是指向根节点
        return union[x];
    }

    private void unionF(int x, int y) {
        //注意这里必须要找根节点 合并集合是将根节点连接到另一个根节点下 不是对叶节点操作
        int a = find(x);
        int b = find(y);
        if (a != b) {
            union[a] = b;
            size--;
        }
    }

}
