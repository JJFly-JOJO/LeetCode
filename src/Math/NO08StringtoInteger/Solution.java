package Math.NO08StringtoInteger;

public class Solution {

    public boolean isDigit(char num) {
        if (num >= '0' && num <= '9')
            return true;
        return false;
    }

    public int myAtoi(String str) {
        int length;
        if (str == null || (length = str.length()) == 0)
            return 0;
        int index = 0;
        while (index < length && str.charAt(index) == ' ') index++;//不能把index++写在charAt中 因为执行到非空格后任然要执行一次++
        //此时index为空格后的第一个字符
        if (index == length) return 0;
        boolean positive;//正负
        char judgePositiveChar = str.charAt(index);
        if (judgePositiveChar == '+') {
            positive = true;
            index++;
        } else if (judgePositiveChar == '-') {
            positive = false;
            index++;
        } else if (isDigit(judgePositiveChar)) {
            positive = true;
        } else {
            return 0;
        }
        //int所能表示的最大正数和负数
        //index指向第一个数
        int limit = positive ? -Integer.MAX_VALUE : Integer.MIN_VALUE;//最大整数的相反数（负数）在最大负数边界中 因此可以统一limit都用负数
        //表示溢出边界 下面的循环不用判断到底是正数还是负数
        int res = 0;

        while (index < length && isDigit(str.charAt(index))) {
            int temp = str.charAt(index++) - '0';
            if (res < (limit + temp) / 10)//注意这里如果是(res*10-temp)与limit比较 那么必须要分别比较res*10 以及 temp
                //一定要小于7（正数）或者小于8（负数） 参考No.7
                return positive ? -limit : limit;
            res = res * 10 - temp;
        }
        return positive ? -res : res;
    }
}
