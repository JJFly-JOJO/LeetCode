package WeekContest.SingleWeek.NO217;

/**
 * @author zzj
 * @version 1.0
 * @date 2020/11/29 11:46
 * @description ------------------差分数组 实现区间更新（通过differ单点的更新）单点查询（查询每个res对应的最少操作数）------------
 */
public class SolutionIII {

    public int minMoves(int[] nums, int limit) {
        //差分数组 枚举2-2*limit互补和所需的最小操作数 再在其中找最小
        //注意+2的操作是为了防止区间更新时 differ[r+1]--->differ[2*limit+1]出现数组越界
        int[] differ = new int[2 * limit + 2];
        int mid = nums.length / 2;
        for (int i = 0; i < mid; i++) {
            //所有元素+2（实际上是差分数组左端点+2）
            differ[2] += 2;
            differ[2 * limit + 1] -= 2;

            int a = nums[i];
            int b = nums[nums.length - i - 1];
            int l = 1 + Math.min(a, b);
            int r = limit + Math.max(a, b);
            //[l,r]区间更新 -1
            differ[l]--;
            differ[r + 1]++;
            //单点a+b更新 -1
            differ[a + b]--;
            differ[a + b + 1]++;
        }
        //find res
        int res = Integer.MAX_VALUE;
        int last = 2 * limit;
        int sum = 0;
        for (int i = 2; i <= last; i++) {
            sum += differ[i];
            if (sum < res) {
                res = sum;
            }
        }
        return res;
    }

}
