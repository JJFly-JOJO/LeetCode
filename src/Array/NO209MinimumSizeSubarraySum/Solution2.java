package Array.NO209MinimumSizeSubarraySum;

import java.util.Arrays;

/**
 * @author zzj
 * @version 1.0
 * @date 2020/10/22 15:01
 * @description ---------------------方法二 暴力搜索O(n2)的优化 利用数组前缀和以及二分查找达到O(nlogn)--------------
 */
public class Solution2 {

    public static void main(String[] args) {
        int[] array = new int[]{1, 2, 3, 5, 5, 5, 5, 9, 10};
        System.out.println(Arrays.binarySearch(array, 11));
        System.out.println(new Solution2().minSubArrayLen(7, new int[]{2, 3, 1, 2, 4, 3}));
    }

    public int minSubArrayLen(int s, int[] nums) {
        int len = nums.length;
        int minLen = Integer.MAX_VALUE;
        if (len == 0) {
            return 0;
        }
        //初始化前缀数组
        //sum[0]表示前0个数之和
        int[] sum = new int[len + 1];
        int val = 0;
        for (int i = 0; i < len; i++) {
            val += nums[i];
            sum[i + 1] = val;
        }
        //O(n)的外层遍历 i表示前0个数之和 我们要找i-j区间满足s的最小区间
        for (int i = 0; i < len; i++) {
            //sum[j]-sum[i]>=s
            int target = sum[i] + s;
            int index = Arrays.binarySearch(sum, target);
            if (index < 0) {
                index = -index - 1;
                if (index == sum.length) {
                    //i越大 需要的sum[j]值越大 j下标越大
                    break;
                }
            }
            minLen = Math.min(minLen, index - i);
        }
        return minLen < Integer.MAX_VALUE ? minLen : 0;
    }
}
