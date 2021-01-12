package WeekContest.SingleWeek.NO213;

import java.util.HashMap;

/**
 * @author zzj
 * @version 1.0
 * @date 2020/11/2 15:19
 * @description ----------利用排列组合公式解决乘法溢出的情况---------
 * <p>
 * c[n][k]=c[n−1][k−1]+c[n−1][k]
 */
public class Solution4V3 {

    public static void main(String[] args) {
        System.out.println(new Solution4V3().kthSmallestPath(new int[]{2, 3}, 1));
    }

    public String kthSmallestPath(int[] destination, int k) {
        int hCount = destination[1];
        int vCount = destination[0];
        int sum = hCount + vCount;
        //预处理排列组合
        int[][] c = new int[sum + 1][hCount + 1];
        //针对n=1初始情况 注意到c[0][0]=c[1][1]=c[2][2]-->c[1][1]+c[1][2](0)
        c[0][0] = 1;
        for (int n = 1; n <= sum; n++) {
            c[n][0] = 1;
            //j<hCount 加快循环速度
            for (int j = 1; j <= n && j < hCount; j++) {
                c[n][j] = c[n - 1][j - 1] + c[n - 1][j];
            }
        }
        StringBuilder str = new StringBuilder();
        for (int i = 0; i < sum; i++) {
            //当前字典序下首选H的可选个数
            int v = hCount - 1;
            int count = v >= 0 ? c[vCount + v][v] : 0;
            if (count >= k) {
                str.append("H");
                hCount--;
            } else {
                str.append("V");
                vCount--;
                k -= count;
            }
        }
        return str.toString();
    }

}
