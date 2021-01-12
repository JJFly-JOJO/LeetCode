package Math.NO01TwoSum;

import java.util.HashMap;
import java.util.Map;

public class Solution {

    public int[] twoSum(int[] nums, int target) {
        int[] res = new int[2];
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] + nums[j] == target) {
                    res[0] = i;
                    res[1] = j;
                    break;
                }
            }
        }
        return res;
    }

    public int[] twoSumForHashTable(int[] nums, int target) {
        int[] res = new int[2];
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            Integer tempIndex = null;
            if ((tempIndex = map.get(target - nums[i])) != null) {
                res[0] = tempIndex;
                res[1] = i;
                return res;
            }
            map.put(nums[i], i);
        }
        return res;
    }

}
