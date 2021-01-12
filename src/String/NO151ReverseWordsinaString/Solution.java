package String.NO151ReverseWordsinaString;

import java.util.*;

/**
 * 注意：Java语言的字符串是不可变的 存在于常量池中
 */
public class Solution {

    private final char SPACE = ' ';

    /**
     * 方法一：使用API 注意正则表达式的使用
     * 方法二： 仿照api的思路 注意Java字符换不可变换在内存中位置
     * 首先将字符串copy到另一个地方 用StringBuilder 这样可以原地在字符串上修改
     * copy时删除多余的空格（先把开头与结尾的多余空格跳过 再从当前边界copy 空格多余的跳过）
     * 编写反转函数
     * 然后将整个字符串反转 在单独对每个单词反转
     *
     * @param s
     * @return
     */
    public String reverseWords(String s) {
        //trim 出去开头和末尾的所有空白字符
        s = s.trim();
        //利用正则表达式 将字符串分割
        String[] sStrs = s.split("\\s+");
        //将数组转化为List(ArrayList)集合
        List<String> list = Arrays.asList(sStrs);
        //将List倒序
        Collections.reverse(list);
        //用空格拼接字符串
        String res = String.join(" ", list);
        return res;
    }

    /**
     * 双端队列(也可以用栈Stack实现 但是考虑到最后可以直接调用String.join(" ",Iterable)的API Stack是没有继承Iterable接口的)
     *
     * @param s
     * @return
     */
    public String reverseWordsForDeque(String s) {
        int length = s.length();
        int left = 0;
        int right = length - 1;
        //去除开头空白字符串-->找到下标
        //注意特殊情况 全是空格
        while (left <= right && s.charAt(left) == SPACE) {
            left++;
        }
        while (left <= right && s.charAt(right) == SPACE) {
            right--;
        }
        if (left == right) {
            return s.substring(left, right + 1);
        }
        //Deque
        Deque<String> deque = new ArrayDeque<>();
        StringBuilder str = new StringBuilder();
        int startIndex = 0;

        while (left <= right) {
            char c = s.charAt(left++);
            if (c != ' ') {
                str.append(c);
            } else {
                while(s.charAt(left)==SPACE){
                    left++;
                }
                deque.offerFirst(str.substring(startIndex, str.length()).toString());
                startIndex = str.length();
            }
        }
        deque.offerFirst(str.substring(startIndex, str.length()).toString());
        return String.join(" ", deque);

    }
}
