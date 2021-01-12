package BitManipulation.NO201BitwiseANDofNumbersRange;

/**
 * @author zzj
 * @version 1.0
 * @date 2020/9/8 17:20
 */
public class Solution {

    public static void main(String[] args) {
        System.out.println(new Solution().rangeBitwiseAndBetter(2, 2));
    }

    /**
     * 技巧：1.对于二进制数 我们进行累加1 进位只会影响到连续的位 也就是说 最高位是不会发生变化的
     * 2.m---->n 每次都是进行的加1操作
     * 3.与操作 只要有0 当前位置只可能为0 m到n之间的数 进行与操作 某个数的某一位为0 则最终结果为0
     * 4.由于加法只影响从低位开始的连续位数 如果我们找mn的最长公共前缀 那么我们就从高位开始找
     * 假设（一共32位） m n的前x位相同，第x+1位不同，那么，由于m < n 则有第x+1位上，m为0 n为1，
     * 那么关键！！！！！从m到n的过程中，一定经过了x100000...这个数k（m<=k<=n),由此 我们只需要找到
     * mn的最长公共前缀 之后所有位都为0（一共32位）
     * <p>
     * 注意题目所给要求：mn都是正整数 也就是第一位符号位一定是0（相同 因此mark我们只需要从第31位开始）mark也不再需要long类型
     *
     * @param m
     * @param n
     * @return
     */
    public int rangeBitwiseAnd(int m, int n) {
        //注意 这里如果是1<<31 那么由于位数没有超出8位 那么mark的实际类型任然是int型 表示的也是Integer.Min（带有符号）负数
        long mark = 1L << 31;
        int count = 0;
        while (mark > 0 && (m & mark) == (n & mark)) {
            count++;
            mark = mark >>> 1;
        }
        //-1为0xffffffff 负数为2的32次方-负数的补码值 负数绝对值越大 补码值越小
        return m & (0xffffffff << (32 - count));
    }

    public int rangeBitwiseAndBetter(int m, int n) {
        //注意 这里如果是1<<31 那么由于位数没有超出8位 那么mark的实际类型任然是int型 表示的也是Integer.Min（带有符号）负数
        int mark = 1 << 30;
        long res = 0;
        long temp;
        while (mark > 0 && (temp = m & mark) == (n & mark)) {
            //<--------------------------------------或的技巧 由于res是0 并且是从左往右开始赋值 没有赋值的都为0
            //由于temp值是表示的单个位上的值 用或时 0是不会影响赋予了1的值的 而res的右边未赋值的都为0
            res |= temp;
            mark = mark >>> 1;
        }
        return (int) res;
    }
}
