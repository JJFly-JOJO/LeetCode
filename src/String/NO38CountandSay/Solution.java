package String.NO38CountandSay;

/**
 * @author zzj
 * @version 1.0
 * @date 2020/9/14 22:23
 */
public class Solution {

    public static void main(String[] args) {
        System.out.println(new Solution().countAndSay(5));
    }

    public String countAndSay(int n) {
        if (n == 1) {
            return "1";
        }
        StringBuilder str = new StringBuilder("1");
        for (int i = 2; i <= n; i++) {
            char[] chars = str.toString().toCharArray();
            str.delete(0, str.length());
            int length = chars.length;
            int last = length - 1;
            for (int j = 0; j < length; j++) {
                int count = 1;
                while (j < last && chars[j] == chars[j + 1]) {
                    count++;
                    j++;
                }
                str.append(count);
                str.append(chars[j]);
            }
            //str.reverse();
        }
        return str.toString();
    }

}
