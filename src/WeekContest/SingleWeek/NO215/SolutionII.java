package WeekContest.SingleWeek.NO215;

/**
 * @author zzj
 * @version 1.0
 * @date 2020/11/16 14:36
 * @description -----------贪心算法----------
 * 思路：
 * 题意的理解！！！交换两个字母位置（位置的交换）---->等价于将一个字母移动到任意位置
 *      交换两个字符值（所有的a替换为b 所有的b替换为a）---->同样类似于将一个字母移动到任意位置！！
 *      只不过每个位置是相同字母的集合
 *
 *      因此第一次遍历交换字母位置 我们只要保证相同的字母在一起 word1 word2相同的字母数量互相对应
 *      那么第二次我们交换字符值就相当于任意移动字母而已
 */
public class SolutionII {

    public boolean closeStrings(String word1, String word2) {
        char[] c1 = word1.toCharArray();
        int l1 = c1.length;
        char[] c2 = word2.toCharArray();
        int l2 = c2.length;
        if (l1 != l2) {
            return false;
        }
        int[] count1 = new int[123];
        int[] count2 = new int[123];
        for (int i = 0; i < l1; i++) {
            count1[c1[i]]++;
            count2[c2[i]]++;
        }
        int[] cp1 = new int[l1 + 1];
        int[] cp2 = new int[l1 + 1];
        for (int i = 97; i <= 122; i++) {
            if (count1[i] == 0 && count2[i] > 0) {
                return false;
            }
            if (count1[i] > 0 && count2[i] == 0) {
                return false;
            }
            cp1[count1[i]]++;
            cp2[count2[i]]++;
        }
        for (int i = 0; i < l1; i++) {
            if (cp1[i] != cp2[i]) {
                return false;
            }
        }
        return true;
    }

}
