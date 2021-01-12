package String.NO76MinimumWindowSubstring;

import java.util.HashMap;
import java.util.Map;

/**
 * @author zzj
 * @version 1.0
 * @date 2020/10/15 14:43
 * @description -----------滑动窗口思想-----------
 */
public class Solution2 {

    Map<Character, Integer> needCount = new HashMap<>();
    Map<Character, Integer> curCount = new HashMap<>();

    public static void main(String[] args) {
        System.out.println(new Solution2().minWindow("aa", "aa"));
    }

    public String minWindow(String s, String t) {
        int lengthT = t.length();
        int lengthS = s.length();
        char[] chars = s.toCharArray();
        //特判
        if (lengthT == 1) {
            return s.contains(t) ? t : "";
        }
        //初始化记录的字符数
        initializeSet(needCount, t);
        int windowLen = Integer.MAX_VALUE;
        int resL = 0;
        int resR = 0;
        int l = 0;
        int r = 0;
        //用来记录判断是否达到满足要求的窗口
        int count = 0;
        //r与l滑动条件：没有找到满足要求的窗口值 滑动r
        //找到满足要求的窗口只 滑动l 直到不满足
        while (l < lengthS) {
            if (count < lengthT) {
                //滑动右窗口
                for (; r < lengthS; r++) {
                    Integer needCharsNum = needCount.get(chars[r]);
                    if (needCharsNum != null) {
                        int val = curCount.getOrDefault(chars[r], 0);
                        curCount.put(chars[r], val + 1);
                        if (val < needCharsNum) {
                            if (++count >= lengthT) {
                                //记录下此时的窗口
                                int winLen = r - l + 1;
                                if (winLen < windowLen) {
                                    windowLen = winLen;
                                    resL = l;
                                    resR = r;
                                }
                                break;
                            }
                        }
                    }
                }
                if (count < lengthT) {
                    break;
                }
            }
            //滑动左窗口
            for (; l < lengthS; l++) {
                if (count < lengthT) {
                    //判断 更新此时的window
                    int winLen = r - l + 2;
                    if (winLen < windowLen) {
                        windowLen = winLen;
                        resL = l - 1;
                        resR = r;
                    }
                    break;
                }
                Integer c = needCount.get(chars[l]);
                if (c == null) {
                    continue;
                }
                int curC;
                curCount.put(chars[l], curC = (curCount.get(chars[l]) - 1));
                if (curC < c) {
                    count--;
                }
            }
            //-------最后一定要r++ 否则下一次进入r的while循环时还是窗口右边界的r而不是一个新的r
            r++;
        }
        return windowLen == Integer.MAX_VALUE ? "" : s.substring(resL, resR + 1);
    }

    private void initializeSet(Map<Character, Integer> charsSet, String t) {
        int length = t.length();
        for (int i = 0; i < length; i++) {
            charsSet.put(t.charAt(i), charsSet.getOrDefault(t.charAt(i), 0) + 1);
        }
    }
}
