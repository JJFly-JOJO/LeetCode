package BitManipulation.NO393UTF8Validation;

/**
 * @author zzj
 * @version 1.0
 *
 * 理解题意！！！第一个字符1的个数代表的该UTF8编码的字符的字节数 那么我们下一次的检查是要从
 *                  i+第一个UTF8编码字节数 的下标开始
 *
 * @date 2020/9/6 13:22
 */
public class Solution {

    public static void main(String[] args) {
        int[] data = new int[]{240, 162, 138, 147, 17};
        System.out.println(new Solution().validUtf8(data));
    }

    /**
     * 可以调用此Integer.toBinaryString(data[i])API 将整数转换为二进制字符串 这样我们就可以很好地获取前n个1
     * 但是字符串操作并不是我们想要的 我们要采用位操作
     * 思考：如何遍历获取到开始的连续的1的个数呢？
     * 我们可以采用100000的掩码 与整数做与操作 从而获得1的个数
     *
     * @param data
     * @return
     */
    public boolean validUtf8Erro(int[] data) {
        int length = data.length;
        //注意审题：每个整数的最低8位才是有效位（低位的一个字节表示UTF8的一个字节）
        //掩码10000000
        int mark = 1 << 7;
        int val = data[0] & mark;
        int countOne = 0;
        while (val != 0) {
            countOne++;
            if (countOne > 4) {
                return false;
            }
            mark = mark >>> 1;
            val = data[0] & mark;
        }
        int l = countOne - 1;
        //特殊情况 [145]---->10010001 只有一个1 UTF-8第一个字节没有一个1的情况（只有为0的情况）
        if (length - 1 < l || l == 0) {
            return false;
        }
        //检验10的掩码
        int mark1 = 1 << 7;
        int mark2 = 1 << 6;
        for (int i = 1; i <= l; i++) {
            if ((data[i] & mark1) != mark1 || (data[i] & mark2) != 0) {
                return false;
            }
        }

        return true;
    }

    /**
     * 上一种方法没有考虑多组UTF8编码的情况
     *
     * @param data
     * @return
     */
    public boolean validUtf8(int[] data) {
        int length = data.length;
        //检验10的掩码
        int mark1 = 1 << 7;
        int mark2 = 1 << 6;
        for (int i = 0; i < length; ) {
            int mark = 1 << 7;
            int val = data[i] & mark;
            int countOne = 0;
            while (val != 0) {
                countOne++;
                if (countOne > 4) {
                    return false;
                }
                mark = mark >>> 1;
                val = data[i] & mark;
            }
            if (countOne == 1) {
                return false;
            }
            if (countOne == 0) {
                i++;
                continue;
            }
            int last = i + countOne - 1;
            if (last > length - 1) {
                return false;
            }
            for (i = i + 1; i <= last; i++) {
                if ((data[i] & mark1) != mark1 || (data[i] & mark2) != 0) {
                    return false;
                }
            }
        }
        return true;
    }

}
