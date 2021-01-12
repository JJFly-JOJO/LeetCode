package String.NO125ValidPalindrome;

/**
 * @author zzj
 * @version 1.0
 * @date 2020/11/21 18:03
 * @description
 */
public class Solution {

    public static void main(String[] args) {
        System.out.println(new Solution().isPalindrome("[^A-Za-z0-9]"));
    }

    public boolean isPalindrome(String s) {
        char[] chars = s.toCharArray();
        int len = chars.length;
        int l = 0;
        int r = len - 1;
        while (l < r) {
            while (l < len && !(chars[l] >= 'a' && chars[l] <= 'z' || chars[l] >= 'A' && chars[l] <= 'Z' || chars[l] >= '0' && chars[l] <= '9')) {
                l++;
            }
            while (r >=0 && !(chars[r] >= 'a' && chars[r] <= 'z' || chars[r] >= 'A' && chars[r] <= 'Z' || chars[r] >= '0' && chars[r] <= '9')) {
                r--;
            }
            if (l >= r) {
                return true;
            }
            if (!(chars[l] == chars[r] || chars[l] - 32 == chars[r] || chars[l] == chars[r] - 32)) {
                return false;
            }
            l++;
            r--;
        }
        return true;
    }

}
