package UnionFind.NO1319NumberofOperationstoMakeNetworkConnected;

/**
 * @author zzj
 * @version 1.0
 * @date 2021/1/23 21:36
 * @description
 */
public class Solution {

    public int makeConnected(int n, int[][] connections) {
        Union u = new Union(n);
        int count = 0;
        for (int[] e : connections) {
            if (u.find(e[0]) != u.find(e[1])) {
                u.unionF(e[0], e[1]);
            } else {
                count++;
            }
        }
        //System.out.println(count+"  "+u.size);
        return count >= u.size - 1 ? u.size - 1 : -1;
    }

    public class Union {
        public int[] union;

        public int size;

        public Union(int s) {
            this.size = s;
            union = new int[s];
            for (int i = 0; i < s; i++) {
                union[i] = i;
            }
        }

        public int find(int x) {
            if (union[x] != x) {
                union[x] = find(union[x]);
            }
            return union[x];
        }

        public void unionF(int x, int y) {
            int a = find(x);
            int b = find(y);
            if (a != b) {
                union[b] = a;
                size--;
            }
        }
    }

}
