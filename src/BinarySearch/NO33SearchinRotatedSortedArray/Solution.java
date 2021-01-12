package BinarySearch.NO33SearchinRotatedSortedArray;

public class Solution {

    public static void main(String[] args) {
        int[] nums = new int[]{2, 1};
        int target = 1;
        System.out.println(new Solution().search(nums, target));
    }

    public int search(int[] nums, int target) {
        if (nums == null || nums.length == 0)
            return -1;
        //先用中点mid区分出有序数组段与无序数组段
        //注意特点left大于right
        int left = 0;
        int right = nums.length - 1;

        while (left < right) {
            int mid = left + (right - left) / 2;

            if (nums[mid] >= nums[left]) {
                //左边段有序
                if (target <= nums[mid] && target >= nums[left]) {
                    //在左侧段寻找 左侧段有序
                    right = mid;
                    while (left < right) {
                        mid = left + (right - left) / 2;
                        if (nums[mid] < target)
                            left = mid + 1;
                        else if (nums[mid] > target)
                            right = mid;
                        else
                            return mid;
                    }
                    if (nums[left] == target)
                        return left;//由于当left=right时 循环终止 此种情况还未考虑
                    return -1;
                } else if (target > nums[mid] || target < nums[left]) {
                    left = mid + 1;
                    continue;
                } else {
                    //正好等于中间值mid
                    return mid;
                }
            } else if (nums[mid] < nums[left]) {
                //右边段有序
                if (target >= nums[mid] && target <= nums[right]) {
                    //在右侧段寻找 右侧段有序
                    left = mid;
                    while (left < right) {
                        mid = left + (right - left) / 2;
                        if (nums[mid] < target)
                            left = mid + 1;
                        else if (nums[mid] > target)
                            right = mid;
                        else
                            return mid;
                    }
                    if (nums[left] == target)
                        return left;//由于当left=right时 循环终止 此种情况还未考虑
                    return -1;
                } else if (target < nums[mid] || target > nums[right]) {
                    right = mid;
                    continue;
                } else
                    return mid;
            }
        }
        if (nums[left] == target)
            return left;//由于当left=right时 循环终止 此种情况还未考虑
        return -1;
    }
}
