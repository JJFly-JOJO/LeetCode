package Math.NO1201UglyNumberIII;

/**
 * @author zzj
 * @version 1.0
 * @date 2020/12/24 13:56
 * @description
 */
public class Solution {

    /**
     * 注意到输入的数最大为10的9次方 当两数相乘时如果用int会造成溢出 因此最小公约数也应该使用long类型
     */
    private long aa;
    private long bb;
    private long cc;
    private long lcmAB;
    private long lcmAC;
    private long lcmBC;
    private long lcmABC;


    public static void main(String[] args) {
        System.out.println(new Solution().nthUglyNumber(1000000000, 2, 217983653, 336916467));
    }

    public int nthUglyNumber(int n, int a, int b, int c) {
        aa = a;
        bb = b;
        cc = c;
        lcmAB = getLeastCommonMulti(aa, bb);
        lcmAC = getLeastCommonMulti(aa, cc);
        lcmBC = getLeastCommonMulti(bb, cc);
        lcmABC = getLeastCommonMulti(lcmAB, cc);
        long lower = Math.min(aa, Math.min(bb, cc));
        long upper = n * lower;
        long res = binarySearch(lower, upper, n);
        return (int) (res - Math.min(res % aa, Math.min(res % bb, res % cc)));
    }

    private long binarySearch(long lower, long upper, int n) {
        long l = lower;
        long r = upper;
        while (l <= r) {
            long mid = (l + r) >>> 1;
            long count = containUglyNumber(mid);
            if (count > n) {
                r = mid - 1;
            } else if (count < n) {
                l = mid + 1;
            } else {
                return mid;
            }
        }
        return l;
    }

    private long containUglyNumber(long num) {
        long countA = num / aa;
        long countB = num / bb;
        long countC = num / cc;
        long countAB = num / lcmAB;
        long countAC = num / lcmAC;
        long countBC = num / lcmBC;
        long countABC = num / lcmABC;
        return countA + countB + countC - countAB - countAC - countBC + countABC;
    }

    private long getLeastCommonMulti(long a, long b) {
        long multi = a * b;
        long divisor = getGreatestCommonDivisor(a, b);
        return multi / divisor;
    }

    private long getGreatestCommonDivisor(long a, long b) {
        while (b > 0) {
            long t = a % b;
            a = b;
            b = t;
        }
        return a;
    }

}
