package WeekContest.SingleWeek.NO209;

import java.util.Arrays;

/**
 * @author zzj
 * @version 1.0
 * @date 2020/10/4 10:18
 * 输入：nums = [0,4,3,0,4]
 * 输出：3
 * 解释：有 3 个元素大于或等于 3 。
 */
public class Solution2 {
    public static void main(String[] args) {
        int[] arr = new int[]{0, 3, 6, 7, 7};
    }

    public int specialArray(int[] nums) {
        int length = nums.length;
        if (length == 0) {
            return -1;
        }
        Arrays.sort(nums);
        int count = 1;
        int i = length - 1;
        if (nums[i] < 1) {
            return -1;
        }
        for (; i >= 0; i--) {
            if (nums[i] >= count) {
                count++;
            } else {
                break;
            }
        }
        count -= 1;
        if (i >= 0 && count == nums[i]) {
            return -1;
        }
        return count;
    }
}
