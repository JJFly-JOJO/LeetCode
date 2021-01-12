package Array.NO219ContainsDuplicateII;

import java.util.HashMap;
import java.util.Map;

/**
 * @author zzj
 * @version 1.0
 * @date 2020/9/8 21:39
 */
public class Solution {

    public boolean containsNearbyDuplicate(int[] nums, int k) {
        //key为val value为index
        Map<Integer, Integer> dictMap = new HashMap<>();
        int length = nums.length;
        for (int i = 0; i < length; i++) {
            Integer tempIndex;
            if ((tempIndex = dictMap.get(nums[i])) != null) {
                if ((i - tempIndex) <= k) {
                    return true;
                }
            }
            dictMap.put(nums[i], i);
        }
        return false;
    }

}
