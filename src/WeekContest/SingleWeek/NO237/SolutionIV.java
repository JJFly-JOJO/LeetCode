package WeekContest.SingleWeek.NO237;

/**
 * @author zzj
 * @version 1.0
 * @date 2021/4/18 11:42
 * @description
 */
public class SolutionIV {

    public static void main(String[] args) {
        System.out.println(new SolutionIV().getXORSum(new int[]{1, 2, 3}, new int[]{6, 5}));
    }

    public int getXORSum(int[] arr1, int[] arr2) {
        int res = 0;
        int and2 = 1;
        for (int e : arr2) {
            and2 ^= e;
        }
        if((arr2.length&1)==0){
            return and2;
        }
        for (int value : arr1) {
            res ^= and2 & value;
        }
        return res;
    }

}
