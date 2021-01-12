package String.NO30SubstringwithConcatenationofAllWords;

import java.util.*;

/**
 * @author zzj
 * @version 1.0
 * @date 2020/10/16 21:32
 * @description 给定一个字符串 s 和一些长度相同的单词 words。找出 s 中恰好可以由 words
 * 中所有单词串联形成的子串的起始位置。
 * <p>
 * 注意子串要与 words 中的单词完全匹配，中间不能有其他字符，但不需要考虑 words 中单词串联的顺序。
 * 输入：
 * s = "barfoothefoobarman",
 * words = ["foo","bar"]
 * 输出：[0,9]
 * 解释：
 * 从索引 0 和 9 开始的子串分别是 "barfoo" 和 "foobar" 。
 * 输出的顺序不重要, [9,0] 也是有效答案。
 * <p>
 * "barfoothefoofoobarman"
 * ["foo","bar","foo"]
 */
public class Solution {

    public static void main(String[] args) {
       /* int j = 0;
        new Solution().test(j, j += 1);
        System.out.println("j=" + j);*/
        for (int temp : new Solution().findSubstring("barfoothefoofoobarman", new String[]{"foo", "bar"})) {
            System.out.println(temp);
        }
    }

    private void test(int i, int j) {
        System.out.println(i + " " + j);
    }

    public List<Integer> findSubstring(String s, String[] words) {
        List<Integer> res = new ArrayList<>();
        int len = words.length;
        int wordLen = words[0].length();
        int windowLen = len * wordLen;
        //boolean[] isVisited=new boolean[len];
        Map<String, Integer> strToCount = new HashMap<>();
        for (String temp : words) {
            strToCount.put(temp, strToCount.getOrDefault(temp, 0) + 1);
        }
        int lastI = s.length() - windowLen;
        Map<String, Integer> tempMap = new HashMap<>();
        Queue<String> visitedStrs = new LinkedList<>();
        for (int i = 0; i < wordLen; i++) {
            int left = i;
            int right = i;
            int lastR = s.length() - wordLen;
            nextL:
            while (left <= lastI) {
                while (right <= lastR) {
                    String temp = s.substring(right, right += wordLen);
                    Integer strCount;
                    if ((strCount = strToCount.get(temp)) == null) {
                        left = right;
                        tempMap.clear();
                        visitedStrs.clear();
                        continue nextL;
                    }
                    int curCount = tempMap.getOrDefault(temp, 0);
                    while (curCount >= strCount) {
                        String popStr = visitedStrs.poll();
                        tempMap.put(popStr, tempMap.get(popStr) - 1);
                        left += wordLen;
                        if (temp.equals(popStr)) {
                            curCount--;
                        }
                    }
                    tempMap.put(temp, curCount + 1);
                    visitedStrs.add(temp);
                    if (visitedStrs.size() == len) {
                        res.add(left);
                        //重置
                        left += wordLen;
                        String popStr = visitedStrs.poll();
                        tempMap.put(popStr, tempMap.get(popStr) - 1);
                        break;
                    }
                }
            }
            tempMap.clear();
            visitedStrs.clear();
        }
        return res;
    }

}
