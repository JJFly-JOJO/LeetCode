package Array.NO75SortColors;

/**
 * @author zzj
 * @version 1.0
 * @date 2020/12/3 14:56
 * @description
 */
public class Solution {

    public void sortColors(int[] nums) {
        int l = 0;
        int r = nums.length - 1;
        int cursor = 0;
        while (cursor <= r) {
            if (nums[cursor] == 2) {
                swap(cursor, r, nums);
                r--;
            } else if (nums[cursor] == 0) {
                /*swap(cursor, l, nums);
                if (cursor == l) {
                    cursor++;
                }
                l++;*/
                //规整思路 cursor与l同时从起点出发
                // （1）l指向的元素为1 cursor此时指向的元素为0 交换后二者都在正确位置 l++ cursor++
                //  (2) l指向的元素为0 cursor与l同处一个下标 那么此时元素在正确位置 l++ cursor++
                swap(cursor,l, nums);
                l++;
                cursor++;
            } else {
                cursor++;
            }
        }
    }

    private void swap(int a, int b, int[] nums) {
        int t = nums[a];
        nums[a] = nums[b];
        nums[b] = t;
    }

}
