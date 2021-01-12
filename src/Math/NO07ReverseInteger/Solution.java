package Math.NO07ReverseInteger;

public class Solution {

    public static final int MAX_INT = 214748364;

    public int reverse(int x) {
        int res = 0;
        while (x != 0) {
            int temp = x % 10;
            if (res > MAX_INT || res == MAX_INT && temp > 7)
                return 0;//overflow
            if (res < -MAX_INT || res == -MAX_INT && temp < -8)
                return 0;
            res = res * 10 + temp;
            x /= 10;
        }
        return res;
    }
}
