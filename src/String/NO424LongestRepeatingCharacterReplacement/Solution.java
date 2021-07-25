package String.NO424LongestRepeatingCharacterReplacement;

/**
 * @author zzj
 * @version 1.0
 * @date 2021/5/28 11:39
 * @description ----------找到一个可能的最值 那么可以跳过中间的值（中间的值只可能比这个值小） 直到找到下一个最大值-------
 */
public class Solution {

    public static void main(String[] args) {
        System.out.println(new Solution().characterReplacement("AABABBA", 1));
    }

    public int characterReplacement(String s, int k) {
        char[] chars = s.toCharArray();
        int l = 0;
        int r = 0;
        int res = 0;
        int[] cntMap = new int[26];
        int maxCnt = 0;
        while (r < chars.length) {
            cntMap[chars[r] - 'A']++;
            maxCnt = Math.max(cntMap[chars[r] - 'A'], maxCnt);
            if (r - l + 1 - maxCnt > k) {
                res = Math.max(res, r - l);
                cntMap[chars[l] - 'A']--;
                l++;
            }
            r++;
        }
        return Math.max(res, r - l);
    }

}
