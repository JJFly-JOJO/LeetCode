package Array.NO217ContainsDuplicate;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * @author zzj
 * @version 1.0
 * @date 2020/9/8 21:10
 */
public class Solution {

    public boolean containsDuplicate(int[] nums) {
        Set<Integer> dict = new HashSet<>();
        for (int i = 0; i < nums.length; i++) {
            if (dict.contains(nums[i])) {
                return true;
            }
            dict.add(nums[i]);
        }
        return false;
    }

    /**
     * 先排序  排序复杂度为logn 再遍历 平均复杂度为logn+n/2 当n很大时 先排序带来的收益更大！！！
     *
     * @param nums
     * @return
     */
    public boolean containsDuplicateForSort(int[] nums) {
        Arrays.sort(nums);
        int length = nums.length;
        for (int i = 0; i < length - 1; i++) {
            if (nums[i] == nums[i + 1]) {
                return true;
            }
        }
        return false;
    }

}
