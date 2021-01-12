package WeekContest.SingleWeek.NO207;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author zzj
 * @version 1.0
 * @date 2020/9/23 15:05
 * <p>
 * 思路：我们可以采用暴力DFS的方法 向左和向右都走 一直走到右下角的坐标停止 遇见0就剪枝 不在DFS 因为0乘任何数都为0
 */

class TestParam {
    public static void inputIntLong(int a, int b) {
        System.out.println("int");
    }

    public static void inputIntLong(long a, long b) {
        System.out.println("long");
    }

    public static void inputIntLong(long a, int b) {
        System.out.println("int long");
    }

}

class None{
    //类中只有一个默认的无参构造方法
}

public class Solution3V2 {

    public static void main(String[] args) {
        /*int[][] grid = new int[][]{{1, -1, 2, 1, -1, 0, 0, 4, 3, 2, 0, -2, -2},
                {-2, 3, 3, -1, -1, 0, 0, -2, 4, -3, 3, 0, 0},
                {-4, -1, -1, -2, 2, -1, -2, -2, 0, 3, -1, -4, 1},
                {-3, 4, -3, 0, -3, 1, -3, 1, 4, 4, -4, -4, -2},
                {3, -3, 1, 0, -1, -4, -4, -4, 3, 2, 2, 3, 3},
                {2, -1, -1, -4, -3, -3, 4, 2, 3, 4, 4, -4, 0},
                {4, -1, 2, -3, -1, -1, -3, -4, 4, 4, 4, -3, -1},
                {-3, -4, 4, -2, -1, 2, 3, -1, 2, 3, 4, 4, -4},
                {-3, -1, -2, 1, 1, -1, -3, -4, -3, 1, -3, 3, -4},
                {2, 4, 4, 4, -3, -3, 1, -1, 3, 4, -1, 1, 4},
                {2, -2, 0, 4, -1, 0, -2, 4, -4, 0, 0, 2, -3},
                {1, 1, -3, 0, -4, -4, -4, -4, 0, -1, -4, -1, 0}, {3, -1, -3, -3, -3, -2, -1, 4, -1, -2, 4, 2, 3}};
        System.out.println(new Solution3V2().maxProductPath(grid));
        List<Integer> list = new ArrayList<Integer>() {
            {
                add(1);
                add(2);
                add(6);
                add(4);
            }
        };
        //升序
        Collections.sort(list, Integer::compareTo);
        System.out.println(list);*/
        int a1 = 0;
        int a2 = 0;
        long b1 = 1L;
        long b2 = 1L;
        // int long-->long long
        TestParam.inputIntLong(b1, b1);
        // long int-->long int
        TestParam.inputIntLong(b1, a1);
        // int int-->int int
        TestParam.inputIntLong(a1, a2);
        new None();
    }

    /**
     * 动态规划
     *
     * @param grid
     * @return
     */
    public int maxProductPath(int[][] grid) {
        int row = grid.length;
        int col = grid[0].length;
        //0对应正数 1对应负数 如果正数不存在 值存-1 如果负数不存在 值存+1
        long[][][] dp = new long[row + 1][col + 1][2];
        //initialize
        for (int j = 0; j <= row; j++) {
            for (int i = 0; i <= col; i++) {
                dp[j][i][0] = -1;
                dp[j][i][1] = 1;
            }
        }
        dp[1][0][0] = 1;
        //dp[1][0][0] = 1;
        //dp[1][0][1] = 1;
        List<Long> rowVal = new ArrayList<>();
        int zero = -1;
        for (int r = 1; r <= row; r++) {
            for (int c = 1; c <= col; c++) {
                long curVal = grid[r - 1][c - 1];
                //0情况要单独考虑----------->如果元素中存在0 那么右下角结果最少是0 也就是即使其他分支结果都是负数
                // 但是只要走0元素的分支 就一定能保证该节点之后的乘积结果都是0
                //同时 0处也不用往下走四个判断分支了 该节点结果一定是0
                if (curVal == 0) {
                    dp[r][c][0] = 0;
                    zero = 0;
                    continue;
                }
                if (dp[r - 1][c][0] >= 0) {
                    rowVal.add(curVal * dp[r - 1][c][0]);
                }
                if (dp[r][c - 1][0] >= 0) {
                    rowVal.add(curVal * dp[r][c - 1][0]);
                }
                if (dp[r - 1][c][1] < 0) {
                    rowVal.add(curVal * dp[r - 1][c][1]);
                }
                if (dp[r][c - 1][1] < 0) {
                    rowVal.add(curVal * dp[r][c - 1][1]);
                }
                //升序 o1>o2 交换顺序
                /*Collections.sort(rowVal, new Comparator<Long>() {
                    @Override
                    public int compare(Long o1, Long o2) {
                        return o1.compareTo(o2);
                    }
                });*/
                //只有一个语句 可以不需要{} 并且返回return关键字可以省略
                //Collections.sort(rowVal, (o1, o2) -> o1.compareTo(o2));
                Collections.sort(rowVal, Long::compareTo);
                //注意易错点-----------------如果comparator我们不是调用的compareTo(该方法的本质是x>y?x==y?) 而是用的o1-o2
                // 那么会出现 正数-负数----->造成数据溢出的情况 因此两数相比较时 如果数存在正负 最好不要用相减判断！！！！！！！！！！
                //最小值
                long min = rowVal.get(0);
                long max = rowVal.get(rowVal.size() - 1);
                if (min < 0) {
                    dp[r][c][1] = min;
                } /*else {
                    dp[r][c][1] = 1;
                }*/
                if (max >= 0) {
                    dp[r][c][0] = max;
                } /*else {
                    dp[r][c][0] = -1;
                }*/
                rowVal.clear();
            }
        }
        return (int) (Math.max(zero, dp[row][col][0]) % (1000000007));
    }

}
