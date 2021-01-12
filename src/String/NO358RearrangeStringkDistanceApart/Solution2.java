package String.NO358RearrangeStringkDistanceApart;

import java.util.Arrays;

/**
 * @author zzj
 * @version 1.0
 * @date 2020/11/9 14:06
 * @description --------------更加精妙的贪心算法-------------
 */
public class Solution2 {

    public static void main(String[] args) {
        System.out.println(new Solution2().rearrangeString("aaadbbcc", 2));
    }

    public String rearrangeString(String s, int k) {
        int[] count = new int[26];
        Integer[] iToC = new Integer[26];
        for (char c : s.toCharArray()) {
            count[c - 'a']++;
        }
        for (int i = 0; i < 26; i++) {
            iToC[i] = i;
        }
        //iToC 从大到小 对应count下标
        Arrays.sort(iToC, (o1, o2) -> count[o2] - count[o1]);
        //找到零元素下标
        int zero = 0;
        while (zero < 26 && count[iToC[zero]] > 0) {
            zero++;
        }
        int index = 0;
        int max = count[iToC[index]];
        //注意java中 创建了自定义类对象数组  我们是要手动对每个数组元素进行赋值的
        //new StringBuilder仅仅是创建了一个引用数组 当前引用内容还是null
        StringBuilder[] sbs = new StringBuilder[max];
        //错误的赋值方法---------------------------------易错点！！！！
        /*for (StringBuilder str : sbs) {
            str = new StringBuilder();
        }*/
        for (int i = 0; i < max; i++) {
            sbs[i] = new StringBuilder();
        }
        while (index < zero) {
            int curIndex = iToC[index];
            int t = count[curIndex] == max ? max : max - 1;
            char c = (char) (curIndex + 'a');
            //技巧：理解这个max--------------------------可以巧妙地实现类似环状数组赋值 for循环到中间时 count为0
            //我们用下一个count的字符来填充 没填充完 下一次循环从i=0的for循环开始 由于count总数是小于max的 因此不会发生重叠
            for (int i = 0; i < t; i++) {
                sbs[i].append(c);
                if (--count[curIndex] == 0) {
                    index++;
                }
                if (index >= zero) {
                    break;
                }
                curIndex = iToC[index];
                c = (char) (curIndex + 'a');
            }
            /*if (index >= zero) {
                break;
            }
            if (count[iToC[index]] == 0) {
                index++;
            }*/
        }
        StringBuilder res = new StringBuilder();
        //注意这里最后一个stringbuilder是不会存在不满足k的情况的 因为后面已经没有字符了
        int last = max - 1;
        for (int i = 0; i < last; i++) {
            if (sbs[i].length() < k) {
                return "";
            }
            res.append(sbs[i]);
        }
        return res.append(sbs[last]).toString();
    }
}
