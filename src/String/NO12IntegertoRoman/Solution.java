package String.NO12IntegertoRoman;

/**
 * @author zzj
 * @version 1.0
 * @date 2020/9/16 16:02
 * <p>
 * 输入: 1994
 * 输出: "MCMXCIV"
 * 解释: M = 1000, CM = 900, XC = 90, IV = 4.
 */
public class Solution {

    public static void main(String[] args) {
        System.out.println(new Solution().intToRoman(3000));
    }

    String[] map = new String[13];
    int[] bucket = new int[13];

    {
        map[0] = "I";
        map[1] = "IV";
        map[2] = "V";
        map[3] = "IX";
        map[4] = "X";
        map[5] = "XL";
        map[6] = "L";
        map[7] = "XC";
        map[8] = "C";
        map[9] = "CD";
        map[10] = "D";
        map[11] = "CM";
        map[12] = "M";

    }

    public String intToRoman(int num) {
        //循环放入桶中
        while (num > 0) {
            if (num >= 1000) {
                int count = num / 1000;
                num -= count * 1000;
                bucket[12] = count;
            } else if (num >= 900) {
                int count = num / 900;
                num -= count * 900;
                bucket[11] = count;
            } else if (num >= 500) {
                int count = num / 500;
                num -= count * 500;
                bucket[10] = count;
            } else if (num >= 400) {
                int count = num / 400;
                num -= count * 400;
                bucket[9] = count;
            } else if (num >= 100) {
                int count = num / 100;
                num -= count * 100;
                bucket[8] = count;
            } else if (num >= 90) {
                int count = num / 90;
                num -= count * 90;
                bucket[7] = count;
            } else if (num >= 50) {
                int count = num / 50;
                num -= count * 50;
                bucket[6] = count;
            } else if (num >= 40) {
                int count = num / 40;
                num -= count * 40;
                bucket[5] = count;
            } else if (num >= 10) {
                int count = num / 10;
                num -= count * 10;
                bucket[4] = count;
            } else if (num == 9) {
                int count = num / 9;
                num -= count * 9;
                bucket[3] = count;
            } else if (num >= 5) {
                int count = num / 5;
                num -= count * 5;
                bucket[2] = count;
            } else if (num == 4) {
                num -= 4;
                bucket[1]++;
            } else if (num >= 1) {
                int count = num / 1;
                num -= count * 1;
                bucket[0] = count;
            }
        }
        StringBuilder res = new StringBuilder();
        for (int i = 12; i >= 0; i--) {
            for (int j = 0; j < bucket[i]; j++) {
                res.append(map[i]);
            }
        }
        return res.toString();
    }

}
