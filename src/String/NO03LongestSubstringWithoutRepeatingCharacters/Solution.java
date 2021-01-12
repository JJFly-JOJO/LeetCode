package String.NO03LongestSubstringWithoutRepeatingCharacters;

import java.util.HashSet;
import java.util.Set;

/**
 * @author zzj
 * @version 1.0
 * @date 2020/10/19 19:14
 * @description
 */
public class Solution {

    public int lengthOfLongestSubstring(String s) {
        int res = 0;
        char[] chars = s.toCharArray();
        int length = chars.length;
        int left = 0;
        int right = 0;
        Set<Character> isVisited = new HashSet<>();
        while (right < length) {
            if (isVisited.contains(chars[right])) {
                char dulChar = chars[right];
                res = Math.max(res, right - left);
                do {
                    isVisited.remove(chars[left]);
                } while (chars[left++] != dulChar);
            }
            isVisited.add(chars[right++]);
        }
        return Math.max(res, right - left);
    }
}
