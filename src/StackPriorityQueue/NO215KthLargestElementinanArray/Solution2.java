package StackPriorityQueue.NO215KthLargestElementinanArray;

/**
 * @author zzj
 * @version 1.0
 * @date 2020/10/9 11:50
 * 输入: [3,2,3,1,2,4,5,5,6] 和 k = 4
 * 输出: 4
 * ----------利用快排中 找到pivot所在的正确下标以及分治思想 来解决此问题-------
 */
public class Solution2 {

    public int findKthLargest(int[] nums, int k) {
        return nums[findK(nums, 0, nums.length - 1, k)];
    }

    private int findK(int[] nums, int left, int right, int k) {
        //少进入一层getPivotIndex函数 k必定是存在的
        if (left == right) {
            return left;
        }
        int fIndex = getPivotIndex(nums, left, right);
        int fTh = nums.length - fIndex;
        if (fTh == k) {
            return fIndex;
        }
        if (fTh > k) {
            return findK(nums, fIndex + 1, right, k);
        } else {
            return findK(nums, left, fIndex - 1, k);
        }
    }

    private int getPivotIndex(int[] nums, int left, int right) {
        int pivotIndex = left + (right - left) / 2;
        swap(nums, pivotIndex, left);
        int pivot = nums[left];
        int j = left;
        for (int i = left + 1; i <= right; i++) {
            if (nums[i] < pivot) {
                j++;
                swap(nums, j, i);
            }
        }
        swap(nums, j, left);
        return j;
    }

    private void swap(int[] nums, int index1, int index2) {
        if (index1 == index2) {
            return;
        }
        int temp = nums[index1];
        nums[index1] = nums[index2];
        nums[index2] = temp;
    }

}
