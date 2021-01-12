package BinarySearch.NO162FindPeakElement;

public class Solution {

    public static void main(String[] args) {
        int[] nums = new int[]{1};
        System.out.println(new Solution().findPeakElement(nums));
    }

    public int findPeakElement(int[] nums) {
        int length = nums.length;
        int left = 0;
        int right = length - 1;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (mid == left) {
                break;
            }
            if (mid - 1 != -1 && nums[mid] < nums[mid - 1]) {
                right = mid;
            } else if (mid + 1 != length && nums[mid] < nums[mid + 1]) {
                left = mid;
            } else {
                return mid;
            }
        }
        if (nums[left] > nums[right]) {
            return left;
        }
        return right;
    }

}
