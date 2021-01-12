package Array.NO11ContainerWithMostWater;

/**
 * @author zzj
 * @version 1.0
 * @date 2020/9/13 15:22
 * 双指针
 * 输入：[1,8,6,2,5,4,8,3,7]
 * 输出：49
 */
public class Solution {

    public static void main(String[] args) {
        int[] nums = new int[]{1, 2, 3, 4, 5};
        int index = 1;
        System.out.println(nums[index] < nums[++index]);
    }

    public int maxArea(int[] height) {
        int length = height.length;
        int leftIndex = 0;
        int rightIndex = length - 1;
        int res = (rightIndex - leftIndex) * Math.min(height[leftIndex], height[rightIndex]);
        while (leftIndex < rightIndex) {
            if (height[leftIndex] < height[rightIndex]) {
                //左边低
                int tempLeft = leftIndex;
                while (tempLeft < rightIndex && height[leftIndex] >= height[++tempLeft]) {
                }
                leftIndex = tempLeft;
            } else {
                //右边低
                int tempRight = rightIndex;
                while (tempRight > leftIndex && height[rightIndex] >= height[--tempRight]) {
                }
                rightIndex = tempRight;
            }
            res = Math.max(res, (rightIndex - leftIndex) * Math.min(height[leftIndex], height[rightIndex]));
        }
        return res;
    }

}
