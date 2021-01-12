package WeekContest.SingleWeek.NO216;

/**
 * @author zzj
 * @version 1.0
 * @date 2020/11/22 10:50
 * @description
 */
public class SolutionIII {

    public static void main(String[] args) {
        System.out.println(new SolutionIII().waysToMakeFair(new int[]{2, 1, 6, 4}));
    }

    public int waysToMakeFair(int[] nums) {
        int len = nums.length;
        int res = 0;
        if(len==0){
            return 0;
        }
        if (len == 1) {
            return 1;
        }

        int[] sum = new int[len];
        sum[0] = nums[0];
        sum[1] = sum[0] + nums[1];
        int[] subNum = new int[len];
        subNum[0] = nums[0];
        subNum[1] = nums[1];
        for (int i = 2; i < len; i++) {
            sum[i] = sum[i - 1] + nums[i];
            subNum[i] = subNum[i - 2] + nums[i];
        }
        int[] sum2 = new int[len];
        sum2[len - 1] = nums[len - 1];
        sum2[len - 2] = nums[len - 1] + nums[len - 2];
        int[] subNum2 = new int[len];
        subNum2[len - 1] = nums[len - 1];
        subNum2[len - 2] = nums[len - 2];
        for (int i = len - 3; i >= 0; i--) {
            sum2[i] = sum2[i + 1] + nums[i];
            subNum2[i] = subNum2[i + 2] + nums[i];
        }
        for (int i = 0; i < len; i++) {
            int headSub1 = 0;
            int headSub2 = 0;
            if (i - 2 >= 0) {
                headSub1 = sum[i - 1] - subNum[i - 1];
            }
            if (i - 1 >= 0) {
                headSub2 = sum[i] - subNum[i];
            }
            int tailSub1 = 0;
            int tailSub2 = 0;
            if (i + 1 < len) {
                tailSub1 = sum2[i] - subNum2[i];
            }
            if (i + 2 < len) {
                tailSub2 = sum2[i + 1] - subNum2[i + 1];
            }
            if (headSub1 + tailSub1 == headSub2 + tailSub2) {
                res++;
            }
        }
        return res;
    }


}
