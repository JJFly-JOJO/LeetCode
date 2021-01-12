package BinarySearch.NO154FindMinimuminRotatedSortedArrayII;

public class Solution {

    public int findMin(int[] nums) {
        int length = nums.length;
        int left = 0;
        int right = length - 1;
        if (nums[left] < nums[right]) {
            return nums[left];
        }
        while (left < right && nums[left] == nums[left + 1]) {
            left++;
        }
        if (left == right) {
            return nums[left];
        }
        while (right > left && nums[right] == nums[right - 1]) {
            right--;
        }
        if (nums[left] < nums[right]) {
            //再次判断 严格保证数组是旋转过了的
            return nums[left];
        }
        while (left < right) {
            int mid = left + (right - left) / 2;
            //经过预处理 可以保证 mid left right 此刻数值都不相等
            if (nums[mid] > nums[left]) {
                left = mid + 1;
                if (nums[left] < nums[mid]) {
                    //超过分界点<-----------可以很好的控制right与left不会出现交叉情况 只会走到else分支后出现相等情况
                    return nums[left];
                }
                while (nums[left] == nums[left + 1]) {
                    left++;
                }
            } else if (nums[mid] < nums[right]) {
                right = mid - 1;
                if (nums[mid] < nums[right]) {
                    //超过分界点
                    return nums[mid];
                }
                while (nums[right] == nums[right - 1]) {
                    right--;
                }
            } else {
                left++;
            }
        }
        return nums[left];
    }

}
