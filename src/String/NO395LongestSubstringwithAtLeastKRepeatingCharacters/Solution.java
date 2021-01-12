package String.NO395LongestSubstringwithAtLeastKRepeatingCharacters;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @author zzj
 * @version 1.0
 * @date 2020/10/21 10:16
 * @description
 */
public class Solution {

    public static void main(String[] args) {
        System.out.println(new Solution().longestSubstring("ababbc", 2));
    }

    /**
     * ----------------------------错误 单纯使用滑动窗口无法解决 因为左右边界是无法保证其中一个边界一定是满足条件的 例如baaabcb------------------
     * ----------------------------当我们以无法满足k的字符条件作为right的停止点 滑动窗口滑到baaab时 我们满足条件的情况是在窗口中间！
     * @param s
     * @param k
     * @return
     */
    public int longestSubstring(String s, int k) {
        //特判
        if (k <= 1) {
            return s.length();
        }
        int[] count = new int[123];
        char[] chars = s.toCharArray();
        for (char c : chars) {
            count[c]++;
        }
        //hashTable for sliding window
        Map<Character, Integer> cToN = new HashMap<>();
        //recording c
        Set<Character> cSet = new HashSet<>();
        //sliding window
        int left = 0;
        int right = 0;
        int l = s.length();
        int res = 0;
        while (right < l) {
            //到达不满足k个数的字符处（window是必不可能越过这个字符） 此时移动左指针 在此界限中找到可行解
            if (count[chars[right]] < k) {
                while (left < right) {
                    if (!cSet.isEmpty()) {
                        int num = cToN.get(chars[left]) - 1;
                        cToN.put(chars[left], num);
                        if (num == 0) {
                            cSet.remove(chars[left]);
                        } else if (num < k) {
                            cSet.add(chars[left]);
                        }
                        left++;
                    } else {
                        res = Math.max(res, right - left);
                        break;
                    }
                }
                //reset
                right++;
                left = right;
                continue;
            }
            int num = cToN.getOrDefault(chars[right], 0) + 1;
            cToN.put(chars[right], num);
            if (num < k) {
                cSet.add(chars[right]);
            } else {
                cSet.remove(chars[right]);
            }
            right++;
        }
        if (cSet.isEmpty()) {
            res = Math.max(res, right - left);
        }
        return res;
    }
}
