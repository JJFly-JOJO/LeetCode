package WeekContest.SingleWeek.NO213;

/**
 * @author zzj
 * @version 1.0
 * @date 2020/11/2 13:53
 * @description ----------------存在乘法溢出的情况------------
 * 注意：该题存在乘法溢出的情况 因此应该使用公式
 * c[n][k]=c[n−1][k−1]+c[n−1][k]
 * [15,15]
 * 1
 */
public class Solution4V2 {

    public static void main(String[] args) {
        System.out.println(new Solution4V2().kthSmallestPath(new int[]{15, 15}, 1));
    }

    public String kthSmallestPath(int[] destination, int k) {
        int hCount = destination[1];
        int vCount = destination[0];
        int resLen = hCount + vCount;
        //前缀数组 记录前缀乘积------------该方法存在乘法溢出的情况！！！
        long[] preArr = new long[resLen + 1];
        preArr[0] = 1;
        long product = 1L;
        for (int i = 1; i <= resLen; i++) {
            product *= i;
            preArr[i] = product;
        }
        //二分查找思路
        StringBuilder res = new StringBuilder();
        while (hCount > 0 && vCount > 0) {
            //当前问题下 头元素选择H（最小字典序）的元素个数 C(h-1 h+v-1)
            int t = hCount - 1;
            long ch = preArr[t + vCount] / preArr[vCount] / preArr[t];
            if (k <= ch) {
                res.append("H");
                hCount--;
            } else {
                res.append("V");
                vCount--;
                k -= ch;
            }
        }
        while (vCount > 0) {
            res.append("V");
            vCount--;
        }
        while (hCount > 0) {
            res.append("H");
            hCount--;
        }
        return res.toString();
    }

}
