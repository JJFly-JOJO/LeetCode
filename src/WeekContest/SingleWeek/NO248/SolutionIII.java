package WeekContest.SingleWeek.NO248;

/**
 * @author zzj
 * @version 1.0
 * @date 2021/7/4 10:56
 * @description
 */
public class SolutionIII {

    private long mod = 1000000007L;

    public static void main(String[] args) {
        System.out.println(new SolutionIII().countGoodNumbers(806166225460393L));
    }

    public int countGoodNumbers(long n) {
        long oddCnt = n / 2;
        long evenCnt = n - oddCnt;
        long oddPower = pow(4, oddCnt);
        long evenPower = pow(5, evenCnt);
        return (int) (oddPower * evenPower % mod);
    }

    private long pow(int num, long cnt) {
        if (cnt == 1) {
            return num;
        }
        if (cnt == 0) {
            return 1;
        }
        //下取整
        long val = pow(num, cnt / 2);
        return (cnt & 1) == 1 ? val * val * num % mod : val * val % mod;
    }

}
