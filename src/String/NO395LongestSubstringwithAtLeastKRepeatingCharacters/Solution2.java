package String.NO395LongestSubstringwithAtLeastKRepeatingCharacters;

/**
 * @author zzj
 * @version 1.0
 * @date 2020/10/21 14:28
 * @description
 */
public class Solution2 {

    private int len = 0;

    public static void main(String[] args) {
        //String s = "123";
        //System.out.println(s.substring(0, 0));
        System.out.println(new Solution2().longestSubstring("baaabcb", 3));
    }

    /**
     * ----------分治 递归解法---------
     *
     * @param s
     * @param k
     * @return
     */
    public int longestSubstring(String s, int k) {
        //特判
        if (k <= 1) {
            return s.length();
        }
        recursive(s, k);
        return len;
    }

    private void recursive(String s, int k) {
        if (s == null || s.equals("")) {
            return;
        }
        int[] cCount = new int[123];
        int length = s.length();
        //记录字符出现的次数
        for (int i = 0; i < length; i++) {
            cCount[s.charAt(i)]++;
        }
        //双指针寻找子串
        int left = 0;
        int right = 0;
        while (right < length && left < length) {
            while (left < length && cCount[s.charAt(left)] < k) {
                left++;
            }
            if (left >= length) {
                break;
            }
            right = left + 1;
            while (right < length && cCount[s.charAt(right)] >= k) {
                right++;
            }
            //终止条件
            if (right - left == length) {
                len = Math.max(len, length);
                break;
            }
            recursive(s.substring(left, right), k);
            left = right + 1;
        }
    }
}
