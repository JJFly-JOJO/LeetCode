package Math.NO09PalindromeNumber;

public class Solution {

    public static void main(String[] args) {
        System.out.println(new Solution().isPalindrome2(122221));
    }

    public boolean isPalindrome(int x) {
        if (x < 0) {
            return false;
        }
        if (x != 0 && x % 10 == 0) {
            //末位不能为0
            return false;
        }
        StringBuilder str = new StringBuilder(Integer.toString(x));
        int length = str.length();
        int mid = length / 2;
        for (int i = 0; i <= mid; i++) {
            if (str.charAt(i) != str.charAt(length - 1 - i)) {
                return false;
            }
        }
        return true;
    }

    /**
     * 直接把回文数计算出来进行比较 注意我们不需要把反转的数完全算完 算到中间那个数即可
     * 如何判断计算到中间那个数了：如果xx小于reverseNum了我们就停止
     * 1.如果位数为奇数 那么就会在中间停止
     * 2.如果位数为偶数 那么如果满足回文数 那么会走到中间停止
     * 3.不是回文数 那么在中间位数会停止
     *
     * @param x
     * @return
     */
    public boolean isPalindrome2(int x) {
        if (x == 0) {
            return true;
        }
        if (x < 0 || x % 10 == 0) {
            return false;
        }
        int xx = x;
        int reverseNum = 0;
        int digit = 0;
        while (reverseNum <= xx) {
            digit = xx % 10;
            reverseNum = reverseNum * 10 + digit;
            xx = xx / 10;
        }
        xx = xx * 10 + digit;
        //一个是奇数位情况 一个是偶数位情况
        return xx - reverseNum == 0 || xx - reverseNum / 10 == 0;
    }

}
