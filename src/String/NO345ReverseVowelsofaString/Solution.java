package String.NO345ReverseVowelsofaString;

import java.util.HashSet;
import java.util.Set;

public class Solution {

    public String reverseVowels(String s) {
        StringBuilder str = new StringBuilder(s);
        int length = s.length();
        int left = 0;
        int right = length - 1;
        //hash表
        //不可行 字符串中有其他字符
        /*int[] dict = new int[26];
        dict[0] = 1;
        dict[4] = 1;
        dict[14] = 1;
        dict[20] = 1;*/
        Set<Integer> dict = new HashSet<Integer>() {
            {
                //非静态初始化代码块
                add(0);
                add(4);
                add(8);
                add(14);
                add(20);
                add('A'-'a');
                add('E'-'a');
                add('I'-'a');
                add('O'-'a');
                add('U'-'a');
            }
        };
        while (left < right) {
            char leftChar='a';
            char rightChar='a';
            while (left<length&&!dict.contains((leftChar = str.charAt(left)) - 'a')) {
                left++;
            }
            while (right>=0&&!dict.contains((rightChar = str.charAt(right)) - 'a')) {
                right--;
            }
            if (left >= right) {
                break;
            }
            char temp = leftChar;
            str.setCharAt(left++, rightChar);
            str.setCharAt(right--, temp);
        }
        return str.toString();
    }

}
