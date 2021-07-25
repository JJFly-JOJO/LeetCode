package Array.NO448FindAllNumbersDisappearedinanArray;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zzj
 * @version 1.0
 * @date 2021/6/3 10:54
 * @description
 */
public class Solution {

    public static void main(String[] args) {
        System.out.println(new Solution().findDisappearedNumbers(new int[]{4, 3, 2, 7, 8, 2, 3, 1}).toString());
    }

    public List<Integer> findDisappearedNumbers(int[] nums) {
        List<Integer> ans = new ArrayList<>();
        int idx = 0;
        while (idx < nums.length) {
            if (nums[idx] - 1 != idx && nums[nums[idx] - 1] != nums[idx]) {
                swap(idx, nums[idx] - 1, nums);
            } else {
                idx++;
            }
        }
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] - 1 != i) {
                ans.add(i + 1);
            }
        }
        return ans;
    }

    private void swap(int a, int b, int[] nums) {
        int t = nums[a];
        nums[a] = nums[b];
        nums[b] = t;
    }

}
