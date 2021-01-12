package WeekContest.SingleWeek.NO210;

/**
 * @author zzj
 * @version 1.0
 * @date 2020/10/11 10:34
 * 输入：s = "(1+(2*3)+((8)/4))+1"
 * 输出：3
 * 解释：数字 8 在嵌套的 3 层括号中。
 * <p>
 * "(1)+((2))+(((3)))"
 */
public class Solution1 {

    public int maxDepth(String s) {
        char[] chars = s.toCharArray();
        int maxDeep = 0;
        int count = 0;
        for (char aChar : chars) {
            if (aChar == '(') {
                count++;
            } else if (aChar == ')') {
                if (count > maxDeep) {
                    maxDeep = count;
                }
                count--;
            }
        }
        return maxDeep;
    }

}
