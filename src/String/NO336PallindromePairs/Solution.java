package String.NO336PallindromePairs;

import java.util.*;

/**
 * @author zzj
 * @version 1.0
 * @date 2021/1/9 18:09
 * @description
 */
public class Solution {

    /**
     * 这里的技巧  为了减少reverse的操作 我们对所有word reverse 而不是对subString reverse
     */
    private Map<String, Integer> sToIndex = new HashMap<>();

    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
        String str = "a";
        //subString 可以得到空串 而非null
        String sub1 = str.substring(str.length(), str.length());
        String sub2 = str.substring(0, 0);
        //空串转char[] 得到length为0数组
        char[] chars = sub1.toCharArray();
        System.out.println(sub1 == null);
        System.out.println(sub2 == null);
        new Solution().palindromePairs(new String[]{"a", "b", "c", "ab", "ac", "aa"});
    }

    public List<List<Integer>> palindromePairs(String[] words) {
        List<List<Integer>> res = new ArrayList<>();
        for (int i = 0; i < words.length; i++) {
            sToIndex.put(new StringBuilder(words[i]).reverse().toString(), i);
        }
        for (int i = 0; i < words.length; i++) {
            char[] chars = words[i].toCharArray();
            int last = chars.length - 1;
            //判断左子串是否是回文串 注意存在["a",""] 空字符串的情况
            for (int left = 0; left <= last; left++) {
                if (isPalindrome(chars, 0, left)) {
                    int index = findIndex(words[i].substring(left + 1, chars.length));
                    if (index >= 0 && index != i) {
                        res.add(Arrays.asList(index, i));
                    }
                }
            }
            //判断右子串是否是回文串
            for (int right = last; right >= 0; right--) {
                if (isPalindrome(chars, right, last)) {
                    int index = findIndex(words[i].substring(0, right));
                    if (index >= 0 && index != i) {
                        res.add(Arrays.asList(i, index));
                    }
                }
            }
            //特判 subString.length==0
            int index = findIndex(words[i]);
            if (index >= 0 && index != i) {
                res.add(Arrays.asList(i, index));
                //如果find 当前元素左右都可以加 形成回文串 但是当遍历到index字符串时 会出现重复add的情况 因此只用将左边增加或者右边增加的结果放入结果集
                //res.add(Arrays.asList(index, i));
            }
        }
        return res;
    }

    /**
     * 注意我们map维护的是倒序后的字符串 也就是只要倒序后的字符串能够跟subString匹配 那么就能够组成回文串
     */
    private int findIndex(String s) {
        return sToIndex.getOrDefault(s, -1);
    }

    private boolean isPalindrome(char[] chars, int l, int r) {
        while (l <= r) {
            if (chars[l] != chars[r]) {
                return false;
            }
            l++;
            r--;
        }
        return true;
    }
}
