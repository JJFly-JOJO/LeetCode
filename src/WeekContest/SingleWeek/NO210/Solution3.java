package WeekContest.SingleWeek.NO210;

/**
 * @author zzj
 * @version 1.0
 * @date 2020/10/11 17:02
 * 输入：a = "ulacfd", b = "jizalu"
 * 输出：true
 * 解释：在下标为 3 处分割：
 * aprefix = "ula", asuffix = "cfd"
 * bprefix = "jiz", bsuffix = "alu"
 * 那么 aprefix + bsuffix = "ula" + "alu" = "ulaalu" 是回文串。
 */
public class Solution3 {

    public boolean checkPalindromeFormation(String a, String b) {
        StringBuilder aStr = new StringBuilder(a);
        StringBuilder bStr = new StringBuilder(b);
        return judge(aStr, bStr) || judge(bStr, aStr);
    }

    private boolean judge(StringBuilder leftStr, StringBuilder rightStr) {
        int left = 0;
        int right = leftStr.length() - 1;
        while (left < right) {
            if (leftStr.charAt(left) != rightStr.charAt(right)) {
                break;
            }
            left++;
            right--;
        }
        if (left >= right) {
            return true;
        }
        return isPalindrome(rightStr.substring(left, right + 1))
                || isPalindrome(leftStr.substring(left, right + 1));
    }

    private boolean isPalindrome(String substring) {
        char[] chars = substring.toCharArray();
        int left = 0;
        int right = chars.length - 1;
        while (left < right) {
            if (chars[left] != chars[right]) {
                break;
            }
            left++;
            right--;
        }
        return left >= right;
    }
}
