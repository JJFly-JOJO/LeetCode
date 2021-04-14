package Offer.NO44;

/**
 * @author zzj
 * @version 1.0
 * @date 2021/3/30 20:15
 * @description
 */
public class Solution {

    public static void main(String[] args) {
        System.out.println(Integer.MAX_VALUE);
        System.out.println(new Solution().findNthDigit(1000000000));
    }

    public int findNthDigit(int n) {
        long digitCount = n + 1;
        long digit = 1;
        long max = 10L;
        long count = digit * max;
        while (digitCount > count) {
            digitCount -= count;
            digit++;
            max *= 10;
            count = (max - max / 10) * digit;
        }
        long startNum = digit == 1 ? 0 : max / 10;
        long mod = digitCount % digit;
        long num = mod == 0 ? startNum + digitCount / digit - 1 : startNum + digitCount / digit;
        String numStr = String.valueOf(num);
        return (mod == 0 ? numStr.charAt(numStr.length() - 1) : numStr.charAt((int) (mod - 1))) - '0';
    }

}
