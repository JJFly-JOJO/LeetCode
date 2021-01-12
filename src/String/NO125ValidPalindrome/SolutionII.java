package String.NO125ValidPalindrome;

/**
 * @author zzj
 * @version 1.0
 * @date 2020/11/23 10:41
 * @description ---------------API String().toLowerCase() 返回的是一个copy的新string-----------------
 */
public class SolutionII {

    public static void main(String[] args) {
        System.out.println("TITLE".toLowerCase());
    }

    public boolean isPalindrome(String s) {
        int len = s.length();
        s = s.toLowerCase();
        char[] chars = s.toCharArray();
        int l = 0;
        int r = len - 1;
        while (l < r) {
            while (l < r && !Character.isLetterOrDigit(chars[l])) {
                l++;
            }
            while (r > l && !Character.isLetterOrDigit(chars[r])) {
                r--;
            }
            if (chars[l] != chars[r]) {
                return false;
            }
            l++;
            r--;
        }
        return true;
    }

}
