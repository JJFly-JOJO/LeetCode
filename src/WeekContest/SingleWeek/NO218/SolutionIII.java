package WeekContest.SingleWeek.NO218;

/**
 * @author zzj
 * @version 1.0
 * @date 2020/12/6 10:43
 * @description 42 727837408
 */
public class SolutionIII {

    public static void main(String[] args) {
        System.out.println(new SolutionIII().concatenatedBinary(42));
    }

    public int concatenatedBinary(int n) {
        long multi = 1L;
        long mod = (long) 1e9 + 7;
        long res = 0L;
        for (int i = n; i > 0; i--) {
            res += i * multi % mod;
            multi = multi * minMax(i) % mod;
        }
        return (int) (res % mod);
    }

    private int minMax(int n) {
        n |= (n >>> 1);
        n |= (n >>> 2);
        n |= (n >>> 4);
        n |= (n >>> 8);
        n |= (n >>> 16);
        n++;
        if (n < 0) {
            n >>>= 1;
        }
        return n;
    }

}
