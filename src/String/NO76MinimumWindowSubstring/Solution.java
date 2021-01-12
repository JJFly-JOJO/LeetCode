package String.NO76MinimumWindowSubstring;

import java.util.HashMap;
import java.util.Map;

/**
 * @author zzj
 * @version 1.0
 * @date 2020/10/14 16:51
 * @description 给你一个字符串 S、一个字符串 T 。请你设计一种算法，
 * 可以在 O(n) 的时间复杂度内，从字符串 S 里面找出：包含 T 所有字符的最小子串。
 * 输入：S = "ADOBECODEBANC", T = "ABC"
 * 输出："BANC"
 * <p>
 * 思路：
 * 滑动窗口 窗口动态缩小 双指针找到窗口
 * <p>
 * 错误数据集：
 * (1)"a""a"---------->需要特判
 * (2)"aa""aa"------->t中存在重复字符 因此不能用HashSet
 * (3)"adobecodebancbbcaa""abc"----->charsSet每次迭代后要clear
 * (4)超时情况------->需要提前判断windowsLen的最小长度 提前退出循环
 */
public class Solution {

    public static void main(String[] args) {
        System.out.println(new Solution().minWindow("adobecodebancbbcaa", "abc"));
    }

    /**
     * -----------------实际上算是暴力解法 遍历n 每一次都要走窗口长度 时间复杂度是O(mn)-----------
     * @param s
     * @param t
     * @return
     */
    public String minWindow(String s, String t) {
        char[] charsOfS = s.toCharArray();
        int tLen = t.length();
        //特判 t的size为1时
        if (tLen == 1) {
            return s.contains(t) ? t : "";
        }
        //发生了new以及拷贝 效率低
        //char[] charsOfT = t.toCharArray();
        //初始窗口大小
        int windowLen = charsOfS.length;
        int leftSide = -1;
        int rightSide = -1;
        //初始化hashtable
        //-------存在重复字符 因此不能用hashSet
        //Set<Character> charsSet = new HashSet<>();
        Map<Character, Integer> charsSet = new HashMap<>();
        initializeSet(charsSet, t);
        int lastL = s.length() - t.length();
        for (int l = 0; l <= lastL; l++) {
            //拷贝
            Map<Character, Integer> tempMap = new HashMap<>(charsSet);
            int val1 = charsSet.getOrDefault(charsOfS[l], 0);
            if (val1 == 0) {
                continue;
            }
            charsSet.put(charsOfS[l], val1 - 1);
            int count = tLen - 1;
            int lastR = l + windowLen - 1;
            for (int r = l + 1; r <= lastR && r < charsOfS.length; r++) {
                int val2 = charsSet.getOrDefault(charsOfS[r], 0);
                if (val2 > 0) {
                    charsSet.put(charsOfS[r], val2 - 1);
                    if (--count == 0) {
                        int len = r - l + 1;
                        if (len <= windowLen) {
                            leftSide = l;
                            rightSide = r;
                            windowLen = len;
                        }
                        break;
                    }
                }
            }
            //特判 提前结束循环
            if (windowLen == tLen) {
                break;
            }
            //复位
            //charsSet.clear();
            //initializeSet(charsSet, t);
            charsSet = tempMap;
        }
        return leftSide == -1 ? "" : s.substring(leftSide, rightSide + 1);
    }

    private void initializeSet(Map<Character, Integer> charsSet, String t) {
        int length = t.length();
        for (int i = 0; i < length; i++) {
            charsSet.put(t.charAt(i), charsSet.getOrDefault(t.charAt(i), 0) + 1);
        }
    }
}
