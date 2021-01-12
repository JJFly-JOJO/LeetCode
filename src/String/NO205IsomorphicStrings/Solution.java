package String.NO205IsomorphicStrings;

import java.util.HashMap;
import java.util.Map;

/**
 * ------------------2n不一定比一次for(n)差-----------------
 *
 */
public class Solution {

    /**
     * 此方法只考虑了字符串仅仅是字母的情况
     * 我们要考虑所有字符 128个
     *
     * @param s
     * @param t
     * @return
     */
    public boolean isIsomorphic(String s, String t) {
        int lengthS = s.length();
        int[] isVisitedS = new int[128];
        int num = 1;
        int curSumS = 0;
        for (int i = 0; i < lengthS; i++) {
            int index = s.charAt(i);
            if (isVisitedS[index] == 0) {
                isVisitedS[index] = num++;
            } else {
                //用正数egg->122来表示
                curSumS = isVisitedS[index] + curSumS * 10;
            }
        }
        lengthS = t.length();
        int[] isVisitedT = new int[128];
        num = 1;
        int curSumT = 0;
        for (int i = 0; i < lengthS; i++) {
            int index = t.charAt(i);
            if (isVisitedT[index] == 0) {
                isVisitedT[index] = num++;
            } else {
                //用正数egg->122来表示
                curSumT = isVisitedT[index] + curSumT * 10;
            }
        }
        if (curSumS - curSumT == 0) {
            return true;
        }
        return false;
    }

    public boolean isIsomorphicForRight(String s, String t) {
        int lengthS = s.length();
        Map<Integer, Integer> isVisited = new HashMap<>();
        int num = 1;
        int curSumS = 0;
        for (int i = 0; i < lengthS; i++) {
            int index = s.charAt(i) - 'a';
            Integer temp;
            if ((temp = isVisited.get(index)) == null) {
                isVisited.put(index, num++);
            } else {
                //用正数egg->122来表示
                curSumS = temp + curSumS * 10;
            }
        }
        lengthS = t.length();
        isVisited.clear();
        num = 1;
        int curSumT = 0;
        for (int i = 0; i < lengthS; i++) {
            int index = t.charAt(i) - 'a';
            Integer temp;
            if ((temp = isVisited.get(index)) == null) {
                isVisited.put(index, num++);
            } else {
                //用正数egg->122来表示
                curSumT = temp + curSumT * 10;
            }
        }
        if (curSumS - curSumT == 0) {
            return true;
        }
        return false;
    }

    /**
     * 方法 ： 注意 计算机中的字符（标准ASCII码字符集）总共128个 因此我们用int[128]的map
     * <p>
     * 上一种方法的改进：一个for循环 边循环边相减（比较），不需要遍历结束后得到最终结果再相减 可以边
     * 遍历边相减（各位相减）
     * <p>
     * 注意 这种方法不一定比第一种方法 分别遍历出结果 然后得到总结果相减好 因为前者可能因为每次遍历的比较
     * 复杂度会达到2n以上 而后者仅仅是2n次赋值即可
     *
     * @param s
     * @param t
     * @return
     */
    public boolean isIsomorphicForOneFor(String s, String t) {
        int length = s.length();
        int[] mapS = new int[128];
        int[] mapT = new int[128];
        int numS = 1;
        //int numT = 1;
        for (int i = 0; i < length; i++) {
            int indexS = s.charAt(i);
            int indexT = t.charAt(i);
            if (mapS[indexS] != mapT[indexT]) {
                //如果一个是0 一个不是0 肯定不同构
                //都不是0 但是numS的迭代数不同 也不同构
                return false;
            } else {
                //如果二者索引对应下标都是0 那表示都是第一次遇见的字符 同样也肯定满足当前位同构
                if (mapS[indexS] == 0) {
                    mapS[indexS] = numS;
                    mapT[indexT] = numS++;
                }
            }
            /*if (mapS[indexS] == 0) {
                mapS[indexS] = numS++;
            }
            if (mapT[indexT] == 0) {
                mapT[indexT] = numT++;
            }
            if (mapS[indexS] != mapT[indexT]) {
                return false;
            }*/
        }
        return true;
    }

}
