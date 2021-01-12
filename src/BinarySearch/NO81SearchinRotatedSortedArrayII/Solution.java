package BinarySearch.NO81SearchinRotatedSortedArrayII;

public class Solution {

    public static void main(String[] args) {
        int[] nums = new int[]{1, 1, 3, 1};
        int target = 3;
        System.out.println(new Solution().search(nums, target));
    }

    //关键存在的问题：
    //10111 和 11101 这种。此种情况下 nums[start] == nums[mid]，
    // 分不清到底是前面有序还是后面有序，此时 start++ 即可。相当于去掉一个重复的干扰项。
    //极端情况[1 1 1 1 1 1 1 1 1 1 0]会导致O(n)遍历

    //二分查找并不要求整个数组完全有序 左半部分有序 右半部分有序都是可以的 只要左右两个部分能完全绝对的区分出特性即可
    public boolean search(int[] nums, int target) {
        if (nums == null || nums.length == 0)
            return false;
        int left = 0;
        int right = nums.length - 1;
        int mid;
        while (left <= right) {//这里如果用left<right 循环结束之后还需要判断left(right)的情况
            mid = left + (right - left) / 2;
            if (nums[mid] == target)
                return true;
            if (nums[mid] == nums[left]) {
                left++;
                continue;
            }
            //前半部分有序
            if (nums[mid] > nums[left]) {
                //target在前半部分
                if (target >= nums[left] && target < nums[mid]) {//target=nums[mid]情况已经在之前判断了
                    right = mid - 1;
                } else {
                    //去后半部分寻找
                    left = mid + 1;
                }
            } else {
                //后半部分有序
                if (target > nums[mid] && target <= nums[right]) {
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            }
        }
        return false;
    }


    public boolean searchErro(int[] nums, int target) {
        if (nums == null || nums.length == 0)
            return false;
        //先用中点mid区分出有序数组段与无序数组段
        //注意特点left大于right
        int left = 0;
        int right = nums.length - 1;

        while (left < right - 1) {
            int mid = left + (right - left) / 2;

            if (nums[mid] > nums[left]) {
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
                            return true;
                    }
                    if (nums[left] == target)
                        return true;//由于当left=right时 循环终止 此种情况还未考虑
                    return false;
                } else if (target > nums[mid] || target < nums[left]) {
                    left = mid + 1;
                    continue;
                } else {
                    //正好等于中间值mid
                    return true;
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
                            return true;
                    }
                    if (nums[left] == target)
                        return true;//由于当left=right时 循环终止 此种情况还未考虑
                    return false;
                } else if (target < nums[mid] || target > nums[right]) {
                    right = mid;
                    continue;
                } else
                    return true;
            } else if (nums[mid] == nums[left]) {
                if (nums[mid + 1] >= nums[mid]) {
                    //左侧全部为相同left值
                    if (target == nums[mid])
                        return true;
                    else {
                        left = mid + 1;
                        continue;
                    }
                }

            }
        }
        for (int i = left; i <= right; i++)
            if (nums[i] == target)
                return true;
        return false;
    }

}
