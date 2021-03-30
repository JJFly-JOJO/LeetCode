package String.NO267PalindromePermutationII;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zzj
 * @version 1.0
 * @date 2021/1/22 11:35
 * @description
 */
public class Solution {
    public static void main(String[] args) {
        System.out.println(new Solution().generatePalindromes("aaaabbbb"));
    }

    /**
     * ASCII码表前128个
     */
    private int[] count = new int[128];
    private Character oddC;
    private boolean[] isVisited;
    private char[] halfChar;

    public List<String> generatePalindromes(String s) {
        List<String> res = new ArrayList<>();
        char[] chars = s.toCharArray();
        for (char c : chars) {
            count[c]++;
        }
        halfChar = new char[s.length() / 2];
        int index = 0;
        oddC = null;
        for (int i = 0; i < count.length; i++) {
            if ((count[i] & 1) == 1) {
                if (oddC == null) {
                    oddC = (char) i;
                } else {
                    return res;
                }
            }
            int c = count[i] / 2;
            for (int j = 0; j < c; j++) {
                halfChar[index++] = (char) i;
            }
        }
        isVisited = new boolean[halfChar.length];
        backtracking(0, new StringBuilder(), res);
        return res;
    }

    private void backtracking(int level, StringBuilder path, List<String> res) {
        if (level == halfChar.length) {
            StringBuilder sb = new StringBuilder(path);
            if (oddC != null) {
                sb.append(oddC);
            }
            sb.append(path.reverse());
            res.add(sb.toString());
            path.reverse();
            return;
        }
        for (int i = 0; i < halfChar.length; i++) {
            //当前层为了防止重复结果的出现 例如aa 如果是第二个a 那么就直接跳过
            //但是要注意：如果第一个a在上一层刚遍历 那么第二个a是不能跳过的 否则会缺少结果
            if (i != 0 && !isVisited[i - 1] && halfChar[i] == halfChar[i - 1] || isVisited[i]) {
                continue;
            }
            path.append(halfChar[i]);
            isVisited[i] = true;
            backtracking(level + 1, path, res);
            isVisited[i] = false;
            path.deleteCharAt(path.length() - 1);
        }
    }

}
