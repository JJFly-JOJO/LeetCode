package DynamicProgramming.NO213HouseRobberII;

/**
 * @author zzj
 * @version 1.0
 * @date 2020/12/23 11:22
 * @description
 */
public class Solution {

    public int rob(int[] nums) {
        if (nums.length == 1) {
            return nums[0];
        }
        if (nums.length == 2) {
            return Math.max(nums[0], nums[1]);
        }
        int max;
        //第一个房屋偷 最后一个不偷
        int first = nums[0];
        int second = Math.max(nums[0], nums[1]);
        for (int i = 2; i < nums.length - 1; i++) {
            int temp = Math.max(first + nums[i], second);
            first = second;
            second = temp;
        }
        max = Math.max(first, second);
        //第一个房屋不偷 最后一个偷
        first = nums[1];
        second = Math.max(nums[1], nums[2]);
        for (int i = 3; i < nums.length; i++) {
            int temp = Math.max(first + nums[i], second);
            first = second;
            second = temp;
        }
        return Math.max(max, Math.max(first, second));
    }

}
