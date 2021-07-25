package WeekContest.SingleWeek.NO242;

/**
 * @author zzj
 * @version 1.0
 * @date 2021/5/23 10:35
 * @description
 */
public class SolutionI {

    public static void main(String[] args) {

        System.out.println(new SolutionI().checkZeroOnes("1101"));
    }

    public boolean checkZeroOnes(String s) {
        int one = 0;
        int zero = 0;
        String[] ones = s.split("0");
        for (String o : ones) {
            one = Math.max(one, o.length());
        }
        String[] zeros = s.split("1");
        for (String z : zeros) {
            zero = Math.max(zero, z.length());
        }
        return one > zero;
    }

}
