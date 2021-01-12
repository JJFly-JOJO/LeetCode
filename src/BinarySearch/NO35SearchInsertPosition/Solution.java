package BinarySearch.NO35SearchInsertPosition;

public class Solution {

    public int searchInsert(int[] nums, int target) {
        //排除[1,3,5,6] 7 target元素在数组外的情况
        if (target > nums[nums.length - 1])
            return nums.length;
        int left = 0;
        int right = nums.length - 1;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] < target) {
                left = mid + 1;//+1防止死循环
            } else if (nums[mid] == target) {
                return mid;
            } else {
                right = mid;
            }
        }
        //没有结果
        //特殊取巧情况 [1,2,3] 3 这时候返回的下标2不管3是不是存在 也就是说如果要查询3以及3以后的数都是返回的最后一个下标2
        return left;
    }

}
