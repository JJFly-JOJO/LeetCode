package WeekContest.SingleWeek.NO241;

/**
 * @author zzj
 * @version 1.0
 * @date 2021/5/16 10:38
 * @description
 */
public class SolutionI {

    private int res = 0;

    public int subsetXORSum(int[] nums) {
        for (int i = 1; i <= nums.length; i++) {
            for (int j = 0; j < nums.length; j++) {
                int xor = nums[j];
                dfs(xor, j, 1, i, nums);
            }
        }
        return res;
    }

    private void dfs(int xor, int idx, int level, int k, int[] nums) {
        if (level >= k) {
            res += xor;
            return;
        }
        for (int i = idx + 1; i < nums.length; i++) {
            dfs(xor ^ nums[i], i, level + 1, k, nums);
        }
    }

}
