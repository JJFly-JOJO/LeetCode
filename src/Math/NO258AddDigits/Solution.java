package Math.NO258AddDigits;

/**
 * 巧妙的O（1）证明！！！！！！！！！！
 * ------------笔记-----------
 */
public class Solution {

    public static void main(String[] args) {
        System.out.println(new Solution().addDigits(12));
        System.out.println(new Solution().addDigits(91));
        System.out.println(new Solution().addDigits(100));
        System.out.println("-----------------");
        System.out.println(new Solution().addDigits(120));
        System.out.println(new Solution().addDigits(121));
        System.out.println(new Solution().addDigits(122));
        System.out.println(new Solution().addDigits(123));
        System.out.println(new Solution().addDigits(124));
        System.out.println(new Solution().addDigits(125));

        System.out.println("----------------");
        System.out.println(new Solution().addDigits(186));
        System.out.println(new Solution().addDigits(187));
        System.out.println(new Solution().addDigits(188));
        System.out.println(new Solution().addDigits(172));

        System.out.println(new Solution().addDigitsForOne(186));
        System.out.println(new Solution().addDigitsForOne(187));
        System.out.println(new Solution().addDigitsForOne(188));
        System.out.println(new Solution().addDigitsForOne(172));

    }

    public int addDigits(int num) {
        int sum = 0;
        while (num != 0) {
            sum += num % 10;
            num /= 10;
        }
        if (sum / 10 == 0) {
            return sum;
        }
        return addDigits(sum);
    }

    public int addDigitsForOne(int num) {
        if (num == 0) {
            return 0;
        }
        return num % 9 == 0 ? 9 : num % 9;
    }

}
