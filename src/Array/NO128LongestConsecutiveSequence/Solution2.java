package Array.NO128LongestConsecutiveSequence;

import java.util.HashSet;
import java.util.Set;

/**
 * @author zzj
 * @version 1.0
 * @date 2020/10/6 18:38
 */
public class Solution2 {

    /**
     * 更精简的写法 不需要用map记录当前元素的后继个数 我们只需要走到连续集的起始点就可以了
     * 如何判断我们当前元素是否是是走到起始点了呢？
     * 如果当前元素的前一个值，例如当前值是1 如果前一个值0不存在 就说明当前值是起始点 从这里找他的后继点
     *
     * @param nums
     * @return
     */
    public int longestConsecutive(int[] nums) {
        Set<Integer> numsSet = new HashSet<>();
        for (int tempNum : nums) {
            numsSet.add(tempNum);
        }
        int max = 0;
        for (int tempNum : numsSet) {
            if (numsSet.contains(tempNum - 1)) {
                continue;
            }
            //当前元素是起始点
            int temp = 1;
            int curNum = tempNum;
            while (numsSet.contains(++curNum)) {
                temp++;
            }
            if (temp > max) {
                max = temp;
            }
        }
        return max;
    }

}
