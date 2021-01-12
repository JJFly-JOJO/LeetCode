package String.NO266PalindromePermutation;

/**
 * @author zzj
 * @version 1.0
 * @date 2020/11/23 10:50
 * @description
 */
public class Solution {

    public boolean canPermutePalindrome(String s) {
        char[] chars = s.toCharArray();
        int len = chars.length;
        if (len == 0) {
            return true;
        }
        int count = (len & 1) == 1 ? 1 : 0;
        int[] num = new int[123];
        for (char c : chars) {
            num[c]++;
        }
        int c = 0;
        for (int i : num) {
            if ((i & 1) == 1) {
                if (++c > count) {
                    return false;
                }
            }
        }
        return true;
    }

}
