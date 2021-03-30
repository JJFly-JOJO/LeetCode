package WeekContest.SingleWeek.NO228;

/**
 * @author zzj
 * @version 1.0
 * @date 2021/2/14 10:39
 * @description
 */
public class SolutionII {

    public static void main(String[] args) {
        System.out.println(new SolutionII().countHomogenous("abc"));
        System.out.println(Integer.MAX_VALUE);
    }

    public int countHomogenous(String s) {
        int max = 1000000007;
        long res = 0L;
        char[] chars = s.toCharArray();
        long r = 0L;
        long l = 0L;
        for (; l < chars.length; l++) {
            if (l != 0 && chars[(int) l] != chars[(int) l - 1]) {
                res = (res + (1 + l - r) * (l - r) / 2) % max;
                r = l;
            }
        }
        return (int) ((res + (1 + l - r) * (l - r) / 2) % max);
    }

}
