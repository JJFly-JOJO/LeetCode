package WeekContest.DoubleWeek.NO45;

/**
 * @author zzj
 * @version 1.0
 * @date 2021/2/6 22:38
 * @description
 */
public class SolutionIII {

    public static void main(String[] args) {
        System.out.println(new SolutionIII().minimumLength("bbbbbbbbbbbbbbbbbbbbbbbbbbbabbbbbbbbbbbbbbbccbcbcbccbbabbb"));
    }

    public int minimumLength(String s) {
        char[] chars = s.toCharArray();
        int l = 0;
        int r = chars.length - 1;
        while (l <= r && canCancel(chars, l, r)) {
            char same = chars[l];
            while (l < chars.length && chars[l] == same) {
                l++;
            }
            while (r >= 0 && chars[r] == same) {
                r--;
            }
        }
        return l <= r ? r - l + 1 : 0;
    }

    private boolean canCancel(char[] chars, int l, int r) {
        return l < r && chars[l] == chars[r];
    }

}
