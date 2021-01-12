package Math.NO29DivideTwoIntegers;

/**
 * 1.计算机如何存储负数：利用同余性质将减法运算通过负数补码的形式转变为了加法运算
 * 余数定理保证了补码的加法运算取模后（由于模数是10000 因此余数就是相加结果本身）
 * 与减法取模后结果相同
 * 2.负数比正数多一个 1000表示负数1000 而0000表示0而非正数
 * 3.以四位数举例：-1的补码16-1=15 -2补码16-2=14.....-8补码为16-8=8 所以负数的补码表示是从
 * 1000（-8的8）开始递增 1001（-7的9）....
 * 4.负数如何求二进制补码表示：本质是16-1（-1的补码）用二进制减 但是通过规律可以看到 是1的二进制全部取反加1
 * 5.负数相反数正数如何求 任然对于负数的补码表示 按位取反加1
 */
public class Solution {

    public static void main(String[] args) {
        System.out.println(new Solution().divideForCycle(-2147483648,
                1));
    }

    /**
     * 基本思路：
     * 为了得到除法结果商 我们需要不断的将除数与被除数相减 直到被除数小于除数时 我们返回结果
     * <p>
     * 鉴于最大负数我们没法用正数表达 我们索性都把正数转换成负数
     *
     * @param dividend
     * @param divisor
     * @return
     */
    public int divide(int dividend, int divisor) {
        //同号为正数 异号负数
        int sign = 1;
        if (dividend > 0) {
            //正数变负数
            dividend = opposite(dividend);
            sign = opposite(sign);
        }
        if (divisor > 0) {
            divisor = opposite(divisor);
            sign = opposite(sign);
        }
        //被除数除数都是负数 被除数更大<--------中断递归条件
        if (dividend > divisor) {
            return 0;
        }

        //商 商也必须用负数表示！！！！！！！！！！因为-2147483648除以-1也会造成结果res溢出
        int res = -1;
        //int count = 1;
        //为了加速搜索 我们每迭代一次 减去被除数就翻倍
        //当不能继续减下去时  我们再将剩下的被除数重新减初始的除数
        //int origin_dividend=dividend;
        int origin_divisor = divisor;
        int origun_dividend = dividend;
        //翻倍减 注意由于翻倍可能会导致除数溢出!!!!!!!!!!!!!!!!!!
        dividend = dividend - divisor;
        while (dividend <= divisor) {
            //翻倍可以用左移运算<<1
            res += res;
            divisor += divisor;
            dividend += opposite(divisor);
        }
        //不满足条件 重新从初始除数减
        //只有第一层可能出现符号异号 但是之后我们都能保证传入的除数与被除数都是同号 并且都是负数 相减是绝对不会溢出的
        //return sign * (res + divide(dividend, origin_divisor));
        //这里传入的是余下数减divisor 多减一个 因为前面的循环中会先加一个res
        int ans = res + opposite(divide(origun_dividend - divisor, origin_divisor));
        if (sign > 0) {
            if (ans == Integer.MIN_VALUE) {
                return Integer.MAX_VALUE;
            }
            return opposite(ans);
        } else {
            return ans;
        }


    }

    /**
     * 取反函数！ 取反加1
     * 我们必须注意到负数比正数多一 也就是 最大的负数 取反 正数（int）类型是无法表达的
     * 就拿四位数来说：最大的负数-8的补码为1000 而我们对-8取反想得到+8 但是补码取反加1任然是1000
     * 也就是说 最大的负数取反任然是负数！！！！！！！
     * <p>
     * 当然由于int的最小负数不能用int的正数表达 我们可以采用long储存
     *
     * @param num
     * @return
     */
    private int opposite(int num) {
        return ~num + 1;
    }


    public int divideForCycle(int dividend, int divisor) {
        boolean differ = (dividend > 0) ^ (divisor > 0);
        dividend = dividend > 0 ? opposite(dividend) : dividend;
        divisor = divisor > 0 ? opposite(divisor) : divisor;
        if (dividend > divisor) {
            return 0;
        }
        int res = -1;
        dividend -= divisor;
        while (dividend <= divisor) {
            int tempRes = -1;
            int tempDivisor = divisor;
            int curDivisor = divisor;
            int curRes = 0;
            while (dividend <= tempDivisor) {
                curDivisor = tempDivisor;
                curRes = tempRes;
                //防止除数溢出 除数不能小于最小整数的一半
                if (tempDivisor <= (Integer.MIN_VALUE >> 1)) {
                    break;
                }
                tempRes = tempRes << 1;
                tempDivisor = tempDivisor << 1;
            }
            res += curRes;
            dividend -= curDivisor;
        }
        if (differ) {
            return res;
        } else {
            if (res == Integer.MIN_VALUE) {
                return Integer.MAX_VALUE;
            }
            return opposite(res);
        }
    }

}
