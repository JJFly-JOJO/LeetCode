package Array.NO283MoveZeroes;

/**
 * @author zzj
 * @version 1.0
 * @date 2020/12/3 15:39
 * @description
 */
public class Solution {

    public void moveZeroes(int[] nums) {
        int len = nums.length;
        int l = 0;
        for (int i = 0; i < len; i++) {
            if (nums[i] != 0) {
                swap(i, l, nums);
                l++;
            }
        }
    }

    private void swap(int a, int b, int[] nums) {
        int t = nums[a];
        nums[a] = nums[b];
        nums[b] = t;
    }

}
