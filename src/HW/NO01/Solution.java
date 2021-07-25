package HW.NO01;

import java.util.Arrays;
import java.util.Scanner;

/**
 * @author zzj
 * @version 1.0
 * @date 2021/7/20 20:00
 * @description
 */
public class Solution {

    public static void main(String[] args) {
        int[] nums = new int[]{11, 9, 2, 10};
        //int[] nums = new int[]{5123, 51234};
        Integer[] res = getResult(nums);
        System.out.println(Arrays.toString(res));
    }
    private static Integer[] getResult(int[] nums) {
        Integer[] integerNum = new Integer[nums.length];
        for (int i = 0; i < nums.length; i++) {
            integerNum[i] = nums[i];
        }
        Arrays.sort(integerNum, (o1, o2) -> {
            String num1 = String.valueOf(o1);
            char[] numChar1 = num1.toCharArray();
            String num2 = String.valueOf(o2);
            char[] numChar2 = num2.toCharArray();
            int idx1 = 0;
            int idx2 = 0;
            while (numChar1[idx1] == numChar2[idx2]) {
                idx1 = (idx1 + 1) % numChar1.length;
                idx2 = (idx2 + 1) % numChar2.length;
            }
            return numChar2[idx2] - numChar1[idx1];
        });
        return integerNum;
    }


}
