package String.NO49GroupAnagrams;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Solution {

    /**
     * 思路还是使用hash表 如何能保证字符相同的string一定hash值相同 而字符不同的string的hash值一定不同
     * <p>
     * **！！这里引出一个算法基本定理：
     * 算术基本定理，又称为正整数的唯一分解定理，即：每个大于1的自然数，要么本身就是质数，要么可以写为2个以上的质数的积，
     * 而且这些质因子按大小排列之后，写法仅有一种方式。
     * 也就是说一个数仅仅只有唯一的一种质数组合（质数值 质数值的个数完全唯一）
     * 由此我们可以利用这个性质构建hash值
     *
     * @param strs
     * @return
     */
    public List<List<String>> groupAnagrams(String[] strs) {
        //每个字母对应一个质数 一共26字母
        int[] prime = {2, 3, 5, 7, 11,
                13, 17, 19, 23, 29,
                31, 37, 41, 43, 47, 53,
                59, 61, 67, 71, 73,
                79, 83, 89, 97, 101};
        Map<Integer, List<String>> mapForRes = new HashMap<>();
        int lengthOgStrs = strs.length;
        for (int i = 0; i < lengthOgStrs; i++) {
            char[] curStr = strs[i].toCharArray();
            int curSum = 1;
            //注意可能有相乘整数溢出的情况 最有可能溢出的情况也就是字符串全部都是zzzzzz...
            //log101(Integer.MAX_VALUE)(z对应的hash值)个z会出现整数溢出
            //此题测试用例不存在此情况
            for (char temp : curStr) {
                curSum *= prime[temp - 'a'];
            }
            List<String> res = mapForRes.get(curSum);
            if (res == null) {
                res = new ArrayList<>();
                res.add(strs[i]);
                mapForRes.put(curSum, res);
            } else {
                res.add(strs[i]);
            }
        }
        return new ArrayList<List<String>>(mapForRes.values());
    }
}
