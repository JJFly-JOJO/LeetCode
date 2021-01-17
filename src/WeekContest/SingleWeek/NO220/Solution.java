package WeekContest.SingleWeek.NO220;

/**
 * @author zzj
 * @version 1.0
 * @date 2020/12/20 10:33
 * @description
 */
public class Solution {

    public static void main(String[] args) {
        System.out.println(new Solution().reformatNumber("1-23-45 6"));
    }

    public String reformatNumber(String number) {
        number = number.replace(" ", "").replace("-", "");
        StringBuilder res = new StringBuilder();
        int count = number.length();
        int i = 0;
        while (count > 4) {
            res.append(number, i, i + 3);
            res.append("-");
            count -= 3;
            i += 3;
        }
        if (count == 0) {
            res.deleteCharAt(res.length() - 1);
            return res.toString();
        }
        if (count == 2 || count == 3) {
            res.append(number.substring(i));
            return res.toString();
        }
        res.append(number, i, i + 2).append("-");
        i += 2;
        res.append(number.substring(i));
        return res.toString();
    }
}
