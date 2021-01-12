package Array.NO34FindFirstandLastPositionofElementinSortedArray;

/**
 * @author zzj
 * @version 1.0
 * @date 2020/12/1 11:47
 * @description
 */
public class Solution {

    public int[] searchRange(int[] nums, int target) {
        int[] res = new int[]{-1, -1};
        if (nums == null || nums.length == 0) {
            return res;
        }
        int index = binaryFind(nums, target);
        if (nums[index] != target) {
            return res;
        }
        int l = index;
        while (l - 1 >= 0 && nums[l - 1] == target) {
            l--;
        }
        int r = index;
        while (r + 1 < nums.length && nums[r + 1] == target) {
            r++;
        }
        res[0] = l;
        res[1] = r;
        return res;
    }

    private int binaryFind(int[] nums, int target) {
        int l = 0;
        int r = nums.length - 1;
        while (l < r) {
            int mid = (l + r) >>> 1;
            if (nums[mid] > target) {
                r = mid;
            } else if (nums[mid] < target) {
                l = mid + 1;
            } else {
                return mid;
            }
        }
        return l;
    }


}
