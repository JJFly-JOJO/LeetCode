package Array.NO169MajorityElement;

import java.util.HashMap;
import java.util.Map;

/**
 * -------------------------------笔记---------------------------
 *  直接遍历所有元素 最大重复元素与其他元素相减 一定能保证为正数 那么我们就从当前元素往后找
 *  只要当前元素与假定的最大数量元素相同 那么计数器加一 如果不同 计数器减一 如果计数器减为0 重置最大数量元素为当前元素
 *
 *
 */
public class Solution {

    public int majorityElement(int[] nums) {
        int length = nums.length;
        Map<Integer, Integer> map = new HashMap<>();
        int max = 0;
        int curElement = 0;
        for (int i = 0; i < length; i++) {
            int count = map.getOrDefault(nums[i], 0) + 1;
            map.put(nums[i], count);
            if (count > max) {
                max = count;
                curElement = nums[i];
            }
        }
        return curElement;
    }

    public int majorityElement2(int[] nums) {
        int length = nums.length;
        Map<Integer, Integer> map = new HashMap<>();
        int max = 0;
        int curElement = 0;
        for (int i = 0; i < length - 1; i = i + 2) {
            if (nums[i] == nums[i + 1]) {
                int count = map.getOrDefault(nums[i], 0) + 2;
                map.put(nums[i], count);
            }
        }
        int count = map.getOrDefault(nums[length - 1], 0) + 1;
        map.put(nums[length - 1], count);
        for (Map.Entry<Integer, Integer> temp : map.entrySet()) {
            if (temp.getValue() > max) {
                max = temp.getValue();
                curElement = temp.getKey();
            }
        }
        return curElement;
    }

    public int majorityElement3(int[] nums) {
        int length = nums.length;
        int max = 0;
        int curElement = nums[0];
        for (int i = 0; i < length - 1; i = i + 2) {
            if (nums[i] == nums[i + 1]) {
                if (max == 0) {
                    curElement = nums[i];
                    max = max + 1;
                } else if (curElement != nums[i]) {
                    max = max - 1;
                } else {
                    max = max + 1;
                }
            }
        }
        if (max == 0) {
            return nums[length - 1];
        }
        return curElement;
    }

}
