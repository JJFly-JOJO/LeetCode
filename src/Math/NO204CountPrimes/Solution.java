package Math.NO204CountPrimes;

import java.util.Arrays;

/**
 * @author zzj
 * @version 1.0
 * @date 2020/9/19 11:20
 * <p>
 * 判断素数通常的方法： 从2遍历到当前数n 判断是否有整除情况
 * // 判断整数 n 是否是素数
 * boolean isPrime(int n) {
 * for (int i = 2; i < n; i++)
 * if (n % i == 0)
 * // 有其他整除因子
 * return false;
 * return true;
 * }
 * 但是上述算法有优化的空间：
 * 两数相乘 如果从小到大遍历的话 小的数遍历到后 就不再需要遍历到另一个被乘数了
 * 以n=12举例：
 * 12 = 2 × 6
 * 12 = 3 × 4
 * 12 = sqrt(12) × sqrt(12)
 * 12 = 4 × 3
 * 12 = 6 × 2
 * 可以看到 sqrt(n)是一个分界点 该之后的遍历其实都已经在之前第一个被乘数是小数的时候遍历到了
 * 因此 for循环我们可以改成for (int i = 2; i < sqrt(n); i++)
 */
public class Solution {

    /**
     * 思路：这里我们不采用从2到n一一遍历判断当前数是否是素数的方法
     * 注意到素数肯定是小于非素数的 也就是说 从小到大遍历 我们肯定先遇到素数
     * 而我们遇到素数后，对该素数进行倍数放大，得到的数都是非素数 由此我们可以
     * 利用一张哈希表 来记录，当遇到一个素数，就对该素数方法，之后的非素数
     * 在哈希表中设置
     *
     * @param n
     * @return
     */
    public int countPrimes(int n) {
        //题意是小于n的数
        boolean[] isPrime = new boolean[n];
        //注意这里还能优化 我们不去要重新对数组赋值为true 直接利用默认值为false即可
        Arrays.fill(isPrime, true);
        //!!!!!!!注意这里提高效率的思路：sqrt是一个分界点 之后的数一定在之前小数翻倍相乘的时候就遍历过了！！！
        //举例：n=26 sqrtN=5 5之后的数我们还需要翻倍遍历吗？
        //比如7 7翻倍：7x2 7x3 7x4->7x2x2...可以看到在2和3翻倍的时候被乘数已经会遍历到7了
        int sqrtN = (int) Math.sqrt(n);
        for (int i = 2; i <= sqrtN; i++) {
            //素数总是会比非素数先出现 因此不需要担心当前i是非素数而造成错误--------可以用反证法
            if (isPrime[i]) {
                //成倍放大j+=i   ix2 ix3 ix4(这里其实也有用到优化思路 利用上一次计算的结果直接加i即可 ix4=ix3+i
                // 而不是直接计算ix4)
                //!!!!!!!!!!注意这里提高效率的思路：原先我们成倍方法是从ix2开始：例如：5x2 5x3 5x4...
                //但是我们注意到 5x2 5x3其实是在2的时候已经放大遍历过了 5x4->5x2x2 小的素数已经把小的非素数（4）
                // 都遍历过了 由此我们还是基于sqrt的中间点的思想 我们从5x5开始遍历
                for (int j = i * i; j < n; j += i) {
                    isPrime[j] = false;
                }
            }
        }
        //计算素数个数
        int count = 0;
        for (int i = 2; i < n; i++) {
            if (isPrime[i]) {
                count++;
            }
        }
        return count;
    }

}
