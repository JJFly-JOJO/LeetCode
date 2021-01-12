package WeekContest.DoubleWeek.NO35;

/**
 * @author zzj
 * @version 1.0
 * @date 2020/9/19 23:02
 * <p>
 * 该动态规划思路存在超时问题 最大问题在于取余% 费时
 */
public class Solution2 {

    public static void main(String[] args) {
        int[] nums = new int[]{6, 3, 5, 2};
        System.out.println(new Solution2().minSubarray(nums, 9));
    }

    public int minSubarray(int[] nums, int p) {
        int length = nums.length;
        //int[][] dp = new int[length + 1][length + 1];
        int[] dp1 = new int[length + 1];
        int[] dp2 = new int[length + 1];
        long sum = 0;
        for (int i = 0; i < length; i++) {
            sum += nums[i];
            dp1[i] = nums[i];
        }
        long modNum = sum % p;
        if (modNum == 0) {
            return 0;
        }
        for (int i = 0; i < length; i++) {
            if (nums[i] % p == modNum) {
                return 1;
            }
        }
        int click = 0;
        for (int i = 2; i <= length - 1; i++) {
            for (int j = 0; j <= length - i; j++) {
                int temp = j + i;
                if (click == 0) {
                    dp2[temp - 1] = dp1[temp - 2] + nums[temp - 1];
                    if (dp2[temp - 1] % p == modNum) {
                        return i;
                    }
                } else {
                    dp1[temp - 1] = dp2[temp - 2] + nums[temp - 1];
                    if (dp1[temp - 1] % p == modNum) {
                        return i;
                    }
                }
            }
            click ^= 1;
        }
        return -1;
    }

    private boolean canMod(long num, int p) {
        while (num > 0) {
            num -= p;
        }
        return num == 0;
    }

}
