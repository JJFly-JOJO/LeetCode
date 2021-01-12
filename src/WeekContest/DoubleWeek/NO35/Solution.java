package WeekContest.DoubleWeek.NO35;

/**
 * @author zzj
 * @version 1.0
 * @date 2020/9/19 22:33
 */
public class Solution {

    public static void main(String[] args) {
        int[] arr = new int[]{1, 4, 2, 5, 3};
        System.out.println(new Solution().sumOddLengthSubarrays(arr));
    }

    public int sumOddLengthSubarrays(int[] arr) {
        int length = arr.length;
        int[][] dp = new int[length + 1][length + 1];
        int res = 0;
        for (int i = 0; i < length; i++) {
            dp[i][i] = arr[i];
            res += arr[i];
        }
        //长度规模
        for (int i = 3; i <= length; i += 2) {
            for (int j = 0; j <= length - i; j++) {
                //0+3=3
                int temp = j + i;
                dp[j][temp - 1] = dp[j][temp - 3] + arr[temp - 1] + arr[temp - 2];
                res += dp[j][temp - 1];
            }
        }
        return res;
    }

}
