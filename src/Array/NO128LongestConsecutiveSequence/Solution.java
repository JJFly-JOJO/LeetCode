package Array.NO128LongestConsecutiveSequence;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @author zzj
 * @version 1.0
 * @date 2020/10/6 16:10
 * 输入: [100, 4, 200, 1, 3, 2]
 * 输出: 4
 * 解释: 最长连续序列是 [1, 2, 3, 4]。它的长度为 4。
 */
public class Solution {

    public static void main(String[] args) {
        System.out.println(new Solution().longestConsecutive(new int[]{2147483646, -2147483647, 0, 2, 2147483644, -2147483645, 2147483645}));
    }

    public int longestConsecutive(int[] nums) {
        Map<Integer, Integer> numToCount = new HashMap<>();
        Set<Integer> numsSet = new HashSet<>();
        for (int num : nums) {
            numToCount.put(num, 1);
            numsSet.add(num);
        }
        int max = 0;
        for (int tempNum : numToCount.keySet()) {
            if (!numsSet.contains(tempNum)) {
                continue;
            }
            int temp = tempNum;
            int curNumCount = numToCount.get(temp);
            while (numsSet.contains(--tempNum)) {
                curNumCount += numToCount.get(tempNum);
                numsSet.remove(tempNum);
            }
            max = Math.max(max, curNumCount);
            numToCount.put(temp, curNumCount);
        }
        return max;
    }

}
