package Offer.NO46;

/**
 * @author zzj
 * @version 1.0
 * @date 2021/3/30 21:42
 * @description
 */
public class Solution {

    public static void main(String[] args) {
        int a=0;
        long b=1L;
        long l =Math.max(a,b);
        System.out.println(new Solution().translateNum(25));
    }

    public int translateNum(int num) {
        String nStr = String.valueOf(num);
        char[] chars = nStr.toCharArray();
        int[] dp = new int[chars.length + 1];
        dp[0] = 1;
        for (int i = 1; i <= chars.length; i++) {
            int count = 0;
            for (int j = i; j > 0; j--) {
                int number = translate(chars, j - 1, i - 1);
                if (number > 25) {
                    break;
                }
                count += dp[j - 1];
            }
            dp[i] = count;
        }
        return dp[chars.length];
    }

    private int translate(char[] chars, int l, int r) {
        //特殊情况 506 不能将06进行解码
        if (chars[l] == '0' && l != r) {
            return 26;
        }
        int num = 0;
        while (l <= r) {
            num = num * 10 + chars[l] - '0';
            l++;
        }
        return num;
    }

}
