package Math.NO50Powxn;

/**
 * 快速幂算法：本质是分治算法 x底数是最基本元素 从底层向上 上层结果是底层结果的平方（二分向上归并）
 * 如果从结果向底层看 也就是x的n次幂向下一层二分x的二分之一n次幂
 */
public class Solution {

    public static void main(String[] args) {
        //System.out.println(Integer.MAX_VALUE);
        //System.out.println(new Solution().myPow(43, 27));
        //System.out.println(2 & 1);
        System.out.println(new Solution().powForCycle(2, 10));
    }

    public double myPow(double x, int n) {
        boolean positive = n >= 0;
        //负数要转换成正数 先除以二 防止转换为正数时溢出 当然也可以用long储存
        int n1 = n / 2;
        int n2 = n - n1;
        if (positive) {
            return powForCycle(x, Math.abs(n1)) * powForCycle(x, Math.abs(n2));
        } else {
            return 1 / (powForCycle(x, Math.abs(n1)) * powForCycle(x, Math.abs(n2)));
        }
    }

    /**
     * 这种递归存在当输入n为2147483647时会有超时问题
     * 因为递归的左右两部分其实返回值完全一样 我们不需要左右分别递归
     *
     * @param x
     * @param n
     * @return
     */
    private double powForRecursive(double x, int n) {
        if (n == 1) {
            return x;
        }
        if (n == 0) {
            return 1;
        }
        return powForRecursive(x, n / 2) * powForRecursive(x, n - n / 2);
    }

    /**
     * 改进 不需要两端递归
     *
     * @param x
     * @param n
     * @return
     */
    private double powForOne(double x, int n) {
        if (n == 0) {
            return 1;
        }
        double y = powForOne(x, n / 2);
        //注意！！！！！！！！！！！！这里不能这么写：
        //                      return n % 2 == 1 ? powForOne(x, n / 2) * powForOne(x, n / 2) * x : powForOne(x, n / 2) * powForOne(x, n / 2);
        //如果这么写 任然是左右两边递归
        //我们只用计算二分的左半边值 右半边值与左半边值相等
        //注意到会出现 5二分为 2 2 1的情况（除以2是向下取整） 因此要判断当前数是奇数还是偶数（或者说 除2是否有余数）
        return n % 2 == 1 ? y * y * x : y * y;
    }

    /**
     * 我们可以从正向迭代x->x2->x4->x9->x19->x38->x77找到二分迭代其实就是77的二进制转换
     * 也可以换一种思路思考 通过77的二进制（计算机中本来就是以二进制表达）来分解指数
     * <p>
     * ex:
     * 9的二进制 1001
     * 对应：
     * x222*x
     * 10的二进制 1010
     * 对应：
     * x222*x2
     *!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!幂运算没有结合律
     * 2的2的3次方 不能先将2的2次方结合再三次方 只能从最高处开始计算 或者等于2的2次方 再2次方 在2次方
     * @param x
     * @param n
     * @return
     */
    public double powForCycle(double x, int n) {
        if (n == 0) {
            return 1;
        }
        double res = 1;
        double cur = x;
        while (n > 0) {
            if ((n & 1) == 1) {
                res *= cur;
            }
            cur *= cur;
            n = n >> 1;
        }
        return res;
    }

}
