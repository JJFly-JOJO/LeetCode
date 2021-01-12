package BitManipulation.NO318MaximumProductofWordLengths;

public class Solution {

    /**
     * 思路：我们不能局限在char的二进制与或非操作能否判断出是否有相同字母的方案上
     * 可以跳出一个char的二进制表达 要利用好0 1，0代表没有 1代表有
     * a-z a在int的第一位为1 表示a字母存在 z在int的第26位 表示z字母的存在
     * 这样 两个字母串例如"abcw"和"baz" 他们用二进制的有无来表达
     * abcw: 1000000 00000000 00000111
     * baz:  100 00000000 00000000 00000011
     * 如果两个字符串完全没有相同的字母 那么这两个二进制有无的表达做相与&运算 结果一定为0
     *
     * @param words
     * @return
     */

    /**
     * System.out.println(0b101);//二进制:5  （0b开头的）
     * System.out.println(0e1011);//0.0
     * System.out.println(011);//八进制:9   (0开头的)
     * System.out.println(11);//十进制:11
     * System.out.println(0x11C);//十六进制:284   （0x开头的）
     */
    private final int WORD_LENGTH = 0b111111;

    public static void main(String[] args) {
        String[] words = new String[]{"abcw", "baz", "foo", "bar", "xtfn", "abcdef"};
        System.out.println(new Solution().maxProduct2(words));
    }

    public int maxProduct(String[] words) {
        //字符串总数
        int lengths = words.length;
        //将字符串用二进制有无来表达
        int[] bitForWord = new int[lengths];
        for (int i = 0; i < lengths; i++) {
            int res = 0;
            int wordLength = words[i].length();
            //32-26=6 低六位用来储存当前word的长度 如果单词长度大于2的6次方 那么次方法不可行
            res = res + wordLength;
            for (int j = 0; j < wordLength; j++) {
                //通过移位运算确定1在哪个位置 都要先移动六位
                //针对["a","aa","aaa","aaaa"]情况 会出现1->0->1->0 因此这里不能用res+ 应该用|运算符
                //res = res + (1 << ((words[i].charAt(j) - 'a') + 6));
                res = res | (1 << ((words[i].charAt(j) - 'a') + 6));

            }
            bitForWord[i] = res;
        }

        //双重循环 两两比较 找到最大单词长度
        int maxLength = 0;
        for (int i = 0; i < lengths; i++) {
            //无符号右移
            int temp = bitForWord[i] >>> 6;
            for (int j = i + 1; j < lengths; j++) {
                if ((temp & (bitForWord[j] >>> 6)) == 0) {
                    int tempLength = (bitForWord[i] & WORD_LENGTH) * (bitForWord[j] & WORD_LENGTH);
                    maxLength = maxLength < tempLength ? tempLength : maxLength;
                }
            }
        }
        return maxLength;
    }

    /**
     * 没有完全利用int空间的方法
     *
     * @param words
     * @return
     */
    public int maxProduct2(String[] words) {
        //字符串总数
        int lengths = words.length;
        //将字符串用二进制有无来表达
        int[] bitForWord = new int[lengths];
        for (int i = 0; i < lengths; i++) {
            int res = 0;
            int wordLength = words[i].length();
            for (int j = 0; j < wordLength; j++) {
                //通过移位运算确定1在哪个位置 都要先移动六位
                //针对["a","aa","aaa","aaaa"]情况 会出现1->0->1->0 因此这里不能用res+ 应该用|运算符
                res = res | (1 << (words[i].charAt(j) - 'a'));

            }
            bitForWord[i] = res;
        }

        //双重循环 两两比较 找到最大单词长度
        int maxLength = 0;
        for (int i = 0; i < lengths; i++) {
            //无符号右移
            int temp = bitForWord[i];
            for (int j = i + 1; j < lengths; j++) {
                if ((temp & bitForWord[j]) == 0) {
                    int tempLength = words[i].length() * words[j].length();
                    maxLength = maxLength < tempLength ? tempLength : maxLength;
                }
            }
        }
        return maxLength;
    }
}
