package Offer.NO51;

/**
 * @author zzj
 * @version 1.0
 * @date 2021/2/4 22:29
 * @description -----------归并排序中寻找结果------------
 */
public class Solution {

    public static void main(String[] args) {
        System.out.println(new Solution().reversePairs(new int[]{7, 5, 6, 4}));
    }

    public int reversePairs(int[] nums) {
        int[] temp = new int[nums.length];
        return dividing(nums, 0, nums.length - 1, temp);
    }

    private int dividing(int[] nums, int l, int r, int[] temp) {
        if (l >= r) {
            return 0;
        }
        int mid = (l + r) >>> 1;
        int leftCount = dividing(nums, l, mid, temp);
        int rightCount = dividing(nums, mid + 1, r, temp);

        int crossCount = merge(nums, l, mid, r, temp);
        return leftCount + rightCount + crossCount;
    }

    /**
     * 归并逻辑
     *
     * @param nums
     * @param l
     * @param mid
     * @param r
     * @param temp
     * @return
     */
    private int merge(int[] nums, int left, int mid, int right, int[] temp) {
        int i = left;
        int l = left;
        int r = mid + 1;
        int count = 0;
        while (i <= right) {
            if (l > mid) {
                temp[i++] = nums[r++];
                continue;
            }
            if (r > right) {
                temp[i++] = nums[l++];
                continue;
            }
            if (nums[l] <= nums[r]) {
                temp[i++] = nums[l++];
            } else {
                count += mid - l + 1;
                temp[i++] = nums[r++];
            }
        }
        //将合并后的内容赋值
        for (int k = left; k <= right; k++) {
            nums[k] = temp[k];
        }
        return count;
    }

}
