package BinarySearch.NO153FindMinimuminRotatedSortedArray;

public class Solution {

    public int findMin(int[] nums) {
        int length = nums.length;
        int left = 0;
        int right = length - 1;
        //排除没有反转的情况
        if (nums[left] < nums[right]) {
            return nums[left];
        }
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] > nums[left]) {
                if (nums[mid] < nums[0]) {
                    //如果已经到了右侧有序列(由于left+1引起的left左侧边界越过了对大值处)
                    //比如 4 5 6 7 0 1 2
                    //left到了7加1->0处 越过了最大右边界值
                    return nums[left];
                }
                left = mid + 1;
            } else if (nums[mid] < nums[right]) {
                right = mid;
            } else if (mid == left) {
                return Math.min(nums[left], nums[right]);
            }
        }
        return nums[left];
    }

}
