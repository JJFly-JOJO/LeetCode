package WeekContest.SingleWeek.NO207;

import java.util.HashSet;
import java.util.Set;

/**
 * @author zzj
 * @version 1.0
 * @date 2020/9/20 10:53
 */
public class Solution2 {

    /*public static void main(String[] args) {
        String s = "addbsd";
        System.out.println(new Solution().maxUniqueSplitErro(s));
    }

    *//**
     * 从中间位置二分dfs 存在困难 左侧递归结束后 递归返回时 isVisited会弹出遍历的值
     * 但是到右侧遍历时 会造成右侧存在子串其实左侧已经遍历过了 但由于isVisited弹出造成人为没有遍历到
     * @param s
     * @return
     *//*
    public int maxUniqueSplitErro(String s) {
        int length = s.length();
        Set<String> isVistited = new HashSet<>();
        int max = 1;
        for (int i = 0; i < length - 1; i++) {
            int temp = 0;
            temp = DFSErro(s.substring(i + 1, length), isVistited);
            if (temp > max) {
                max = temp;
            }
        }
        return max;
    }

    private int DFSErro(String rRubstring,
                    Set<String> isVistited) {
        if (isVistited.contains(lSubstring) || isVistited.contains(rRubstring) || lSubstring.equals(rRubstring)) {
            return 1;
        }
        isVistited.add(lSubstring);
        isVistited.add(rRubstring);
        int lMax = 1;
        int rMax = 1;
        int lLength = lSubstring.length();
        for (int i = 0; i < lLength - 1; i++) {
            int temp = 0;
            temp = DFSErro(lSubstring.substring(0, i + 1), lSubstring.substring(i + 1, lLength), isVistited);
            if (temp > lMax) {
                lMax = temp;
            }
        }
        int rLength = rRubstring.length();
        for (int i = 0; i < rLength - 1; i++) {
            int temp = 0;
            temp = DFSErro(rRubstring.substring(0, i + 1), rRubstring.substring(i + 1, rLength), isVistited);
            if (temp > rMax) {
                rMax = temp;
            }
        }
        //isVistited.remove(lSubstring);
        //isVistited.remove(rRubstring);
        return lMax + rMax;
    }*/

}
