package String.NO186ReverseWordsinaStringII;

/**
 * @author zzj
 * @version 1.0
 * @date 2020/10/29 14:14
 * @description
 */
public class Solution {

    public void reverseWords(char[] s) {
        //先反转字符数组
        int len = s.length;
        int left = 0;
        int right = len - 1;
        reverseWord(s, left, right);
        //反转单个字符
        int mark = 0;
        for (int i = 0; i < len; i++) {
            if (s[i] == ' ') {
                reverseWord(s, mark, i - 1);
                mark = i + 1;
            }
        }
        reverseWord(s, mark, right);
    }

    private void reverseWord(char[] s, int left, int right) {
        while (left < right) {
            char t = s[left];
            s[left] = s[right];
            s[right] = t;
            left++;
            right--;
        }
    }

}
