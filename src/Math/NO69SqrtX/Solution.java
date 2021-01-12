package Math.NO69SqrtX;

public class Solution {

    public static void main(String[] args) {
        System.out.println(new Solution().mySqrt(1593654));
    }

    public int mySqrt(int x) {
        int left = 0;
        int right = x;
        while (left < right) {
            int mid = (left + right) / 2;
            //存在溢出情况
            if ((long) mid * mid > x) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        if ((long) left * left > x) {
            return left - 1;
        }
        return left;
    }

}
