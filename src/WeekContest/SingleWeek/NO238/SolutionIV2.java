package WeekContest.SingleWeek.NO238;

import java.util.Arrays;

/**
 * @author zzj
 * @version 1.0
 * @date 2021/5/10 11:36
 * @description
 */
public class SolutionIV2 {

    public static void main(String[] args) {
        System.out.println(new SolutionIV2().maxBuilding(5, new int[][]{{2, 1}, {4, 1}}));
    }

    public int maxBuilding(int n, int[][] restrictions) {
        //隐藏的两个边界 在建筑1处高度限制为0 在建筑n处高度限制为n-1
        //添加两个边界的目的是形成闭区间
        int[][] restr = new int[restrictions.length + 2][];
        restr[0] = new int[]{1, 0};
        restr[1] = new int[]{n, n - 1};
        System.arraycopy(restrictions, 0, restr, 2, restrictions.length);
        Arrays.sort(restr, (o1, o2) -> o1[0] - o2[0]);
        //两次遍历 一次左遍历 一次右遍历
        //left->right
        for (int i = 1; i < restr.length; i++) {
            restr[i][1] = Math.min(restr[i][1], restr[i - 1][1] + restr[i][0] - restr[i - 1][0]);
        }
        //right->left
        for (int i = restr.length - 2; i >= 0; i--) {
            restr[i][1] = Math.min(restr[i][1], restr[i + 1][1] + restr[i + 1][0] - restr[i][0]);
        }
        //找到每个间隔的峰值
        //i limitI j limitJ:(max-limitI)+(max-limitJ)<=j-i(抓住相邻相差最多为1)
        //max<=(j-i+limitI+limitJ)/2
        int res = 0;
        for (int i = 1; i < restr.length; i++) {
            res = Math.max(res, (restr[i][0] - restr[i - 1][0] + restr[i][1] + restr[i - 1][1]) / 2);
        }
        return res;
    }

}
