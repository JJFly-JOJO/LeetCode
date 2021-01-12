package String.NO294FlipGameII;

/**
 * @author zzj
 * @version 1.0
 * @date 2020/11/6 16:39
 * @description -------------------错误原因未知----------------
 */
public class Solution {

    private boolean reach = false;

    public static void main(String[] args) {
        System.out.println(new Solution().canWin("++++++"));
    }

    public boolean canWin(String s) {
        char[] chars = s.toCharArray();
        int len = chars.length;
        for (int i = 1; i < len; i++) {
            if (chars[i] == '+' && chars[i - 1] == '+') {
                chars[i] = '-';
                chars[i - 1] = '-';
                dfs(chars, 0);
                if (!reach) {
                    return true;
                }
                chars[i] = '+';
                chars[i - 1] = '+';
                reach = false;
            }
        }
        return false;
    }

    private void dfs(char[] chars, int player) {
        if (reach) {
            return;
        }
        boolean enter = false;
        for (int i = 1; i < chars.length; i++) {
            if (chars[i] == '+' && chars[i - 1] == '+') {
                enter = true;
                chars[i] = '-';
                chars[i - 1] = '-';
                dfs(chars, player ^ 1);
                chars[i] = '+';
                chars[i - 1] = '+';
            }
        }
        if (!enter && player == 1) {
            reach = true;
        }
    }

}
