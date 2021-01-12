package WeekContest.DoubleWeek.NO39;

/**
 * @author zzj
 * @version 1.0
 * @date 2020/11/14 23:09
 * @description -----------贪心算法 也类似动态规划 每遇到错误的a位置 有两种删除情况 我们更新当前局部最优解---------
 */
public class SolutionII {

    private int res = Integer.MAX_VALUE;

    public static void main(String[] args) {
        System.out.println(new SolutionII().minimumDeletions("aababbab"));
    }

    public int minimumDeletions(String s) {
        int len = s.length();
        if (len <= 1) {
            return 0;
        }
        char[] chars = s.toCharArray();
        int numA = 0;
        for (char c : chars) {
            if (c == 'a') {
                numA++;
            }
        }
        if (numA == 0 || numA == len) {
            return 0;
        }
        //deletB记录已经删除的B的个数
        int deletB = 0;
        int countB = 0;
        for (int i = 0; i < len; i++) {
            if (chars[i] == 'a') {
                if (i != 0 && chars[i - 1] != 'a') {
                    //情况一：当前a以及后面的a全部删除的情况  类似动态规划
                    res = Math.min(numA + deletB, res);
                    //情况二：或者全部删除前面的B
                    deletB = countB;
                }
                numA--;
            } else {
                countB++;
            }
        }
        return Math.min(res, deletB);
    }

}
