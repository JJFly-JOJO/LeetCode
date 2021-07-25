package WeekContest.SingleWeek.NO251;

/**
 * @author zzj
 * @version 1.0
 * @date 2021/7/25 10:28
 * @description
 */
public class SolutionI {

    public int getLucky(String s, int k) {
        StringBuilder sb = new StringBuilder();
        char[] chars = s.toCharArray();
        for (char c : chars) {
            sb.append((c - 'a' + 1));
        }
        String numStr = sb.toString();
        int num = changeStr(numStr);
        for (int i = 1; i < k; i++) {
            num = change(num);
        }
        return num;
    }

    private int changeStr(String numStr) {
        char[] chars = numStr.toCharArray();
        int sum = 0;
        for (char c : chars) {
            sum += c - '0';
        }
        return sum;
    }

    private int change(int num) {
        int sum = 0;
        while (num != 0) {
            sum += num % 10;
            num /= 10;
        }
        return sum;
    }

}
