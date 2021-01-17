package WeekContest.SingleWeek.NO220;

import java.util.Arrays;

/**
 * @author zzj
 * @version 1.0
 * @date 2020/12/20 11:28
 * @description ----------------离线思想 对输入数据的重构--------------
 */
public class SolutionIV {

    private int[] union;

    public boolean[] distanceLimitedPathsExist(int n, int[][] edgeList, int[][] queries) {
        Integer[] dict = new Integer[queries.length];
        for (int i = 0; i < dict.length; i++) {
            dict[i] = i;
        }
        Arrays.sort(dict, (o1, o2) -> queries[o1][2] - queries[o2][2]);
        Arrays.sort(edgeList, (o1, o2) -> o1[2] - o2[2]);
        boolean[] res = new boolean[queries.length];
        union = new int[n];
        for (int i = 0; i < union.length; i++) {
            union[i] = i;
        }
        int index = 0;
        for (Integer integer : dict) {
            while (index < edgeList.length && edgeList[index][2] < queries[integer][2]) {
                unionF(edgeList[index][0], edgeList[index][1]);
                index++;
            }
            res[integer] = find(queries[integer][0]) == find(queries[integer][1]);
        }
        return res;
    }

    private void unionF(int x, int y) {
        int a = find(x);
        int b = find(y);
        if (a != b) {
            union[b] = union[a];
        }
    }

    private int find(int x) {
        if (union[x] != x) {
            union[x] = find(union[x]);
        }
        return union[x];
    }

}
