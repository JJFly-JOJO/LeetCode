package BitManipulation.NO389FindtheDifference;

public class Solution {
    /**
     * 位运算异或的理解：
     * 异或，两个相同元素异或之后的值是0，0和x(任何数)异或等于x，还有一点非常重要：就是不管两个相同的数是在什么时候异或的，最终的结果都会存在0
     * 我举个例子：假如有6个数字：2 3 4 4 3 2， 不管是2^3^4^4^3^2 还是我们经过处理之后组合起来 (2^2)^(3^3)^(4^4)结果都是一样的，不会影响结果
     * 那我们想下本题两个字符串中的字符，s和t中相同的字符都存在两个，将他们全部异或之后肯定为0，然后其中还有一个多出来的就成了0^x=x，从而得到结果
     *
     * @param s
     * @param t
     * @return
     */
    public char findTheDifference(String s, String t) {
        int lengthOfS = s.length();
        int res = 0;
        for (int i = 0; i < lengthOfS; i++) {
            res = s.charAt(i) ^ t.charAt(i) ^ res;
        }
        return (char) (res ^ t.charAt(lengthOfS));

    }
}
