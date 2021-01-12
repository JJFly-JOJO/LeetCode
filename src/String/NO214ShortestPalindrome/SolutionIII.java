package String.NO214ShortestPalindrome;

/**
 * @author zzj
 * @version 1.0
 * @date 2020/11/25 15:50
 * @description -----------------使用hash表的思路 用质数来避免hash碰撞（投机取巧）-----------------
 * 一般来说，我们选取一个大于字符集大小（即字符串中可能出现的字符种类的数目）的质数作为 \textit{base}base，
 * 再选取一个在字符串长度平方级别左右的质数作为 \textit{mod}mod，产生哈希碰撞的概率就会很低。
 */
public class SolutionIII {

    public String shortestPalindrome(String s) {
        int base = 131;
        long mod = (long) 1e9 + 7;
        int index = 0;
        int i = 0;
        long hashLeft = 0L;
        long hashRight = 0L;
        char[] chars = s.toCharArray();
        int len = chars.length;
        long mul = 1L;
        while (index < len) {
            hashLeft = (hashLeft + chars[index] * mul) % mod;
            hashRight = (hashRight * base + chars[index]) % mod;
            index++;
            if (hashLeft == hashRight) {
                i = index;
            }
            mul = (mul * base) % mod;
        }
        return new StringBuilder(s).insert(0, new StringBuilder(s.substring(i, len)).reverse()).toString();
    }
}
