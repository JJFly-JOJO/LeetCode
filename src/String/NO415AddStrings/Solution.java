package String.NO415AddStrings;

/**
 * @author zzj
 * @version 1.0
 * @date 2021/6/3 11:28
 * @description
 */
public class Solution {

    public static void main(String[] args) {
        System.out.println(new Solution().addStrings("11", "123"));
    }

    public String addStrings(String num1, String num2) {
        StringBuilder sb1 = new StringBuilder(num1);
        StringBuilder sb2 = new StringBuilder(num2);
        StringBuilder ans = new StringBuilder();
        int idx1 = sb1.length() - 1;
        int idx2 = sb2.length() - 1;
        int digit = 0;
        while (idx1 >= 0 && idx2 >= 0) {
            int addVal = sb1.charAt(idx1) - '0' + sb2.charAt(idx2) - '0' + digit;
            digit = addVal / 10;
            ans.append(addVal % 10);
            idx1--;
            idx2--;
        }
        while (idx1 >= 0) {
            int addVal = sb1.charAt(idx1) - '0' + digit;
            digit = addVal / 10;
            ans.append(addVal % 10);
            idx1--;
        }
        while (idx2 >= 0) {
            int addVal = sb2.charAt(idx2) - '0' + digit;
            digit = addVal / 10;
            ans.append(addVal % 10);
            idx2--;
        }
        if (digit == 1) {
            ans.append(1);
        }
        return ans.reverse().toString();
    }

}
