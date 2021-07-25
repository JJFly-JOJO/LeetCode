package HW.NO02;

/**
 * @author zzj
 * @version 1.0
 * @date 2021/7/22 19:48
 * @description
 */
public class Solution {

    private static int ans = 0;

    public static void main(String[] args) {
        //String str1="rabbbit";
        //String str2="rabbit";
        String str1 = "babgbag";
        String str2 = "bag";
        int cnt = getResult(str1, str2);
        System.out.println(cnt);
    }

    private static int getResult(String str1, String str2) {
        char[] char1 = str1.toCharArray();
        char[] char2 = str2.toCharArray();
        for (int i = 0; i < char1.length; i++) {
            backtracking(i, 0, char1, char2);
        }
        return ans;
    }

    private static void backtracking(int idx1, int idx2, char[] char1, char[] char2) {
        if (idx1 >= char1.length) {
            return;
        }
        if (idx2 == char2.length - 1) {
            if (char1[idx1] == char2[idx2]) {
                ans++;
            }
            return;
        }
        if (char1[idx1] != char2[idx2]) {
            return;
        }

        for (int i = idx1 + 1; i < char1.length; i++) {
            backtracking(i, idx2 + 1, char1, char2);
        }
    }

}
