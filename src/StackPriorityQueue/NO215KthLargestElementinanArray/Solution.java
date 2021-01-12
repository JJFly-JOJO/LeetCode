package StackPriorityQueue.NO215KthLargestElementinanArray;

import java.util.Arrays;

/**
 * @author zzj
 * @version 1.0
 * @date 2020/10/2 13:06
 * -------------解法一 快速排序（暴力解法）----------
 * 快速排序时间复杂度：O（NlogN）
 */
public class Solution {

    public int findKthLargest(int[] nums, int k) {
        Arrays.sort(nums);
        return nums[nums.length - k];
    }
}
