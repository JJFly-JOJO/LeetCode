package Greedy.NO1689PartitioningIntoMinimumNumberOfDeciBinaryNumbers;

/**
 * @author zzj
 * @version 1.0
 * @date 2020/12/14 10:06
 * @description
 */
public class Solution {

    public int minPartitions(String n) {
        char[] chars = n.toCharArray();
        int max = 0;
        for (char aChar : chars) {
            int v = aChar - '0';
            if (v > max) {
                max = v;
                if (max == 9) {
                    return max;
                }
            }
        }
        return max;
    }

}
