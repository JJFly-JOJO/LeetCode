package DynamicProgramming.NO198HouseRobber;

/**
 * @author zzj
 * @version 1.0
 * @date 2020/12/22 13:56
 * @description ------------使用滚动数组实现状态压缩-------------
 */
public class SolutionII {

    public int rob(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }
        if (nums.length == 1) {
            return nums[0];
        }
        int first = nums[0];
        int second = Math.max(nums[1], nums[0]);
        for (int i = 2; i < nums.length; i++) {
            int temp = Math.max(second, first + nums[i]);
            first = second;
            second = temp;
        }
        return Math.max(first, second);

    }
}
