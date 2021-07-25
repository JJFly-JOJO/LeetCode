package WeekContest.SingleWeek.NO239;

/**
 * @author zzj
 * @version 1.0
 * @date 2021/5/2 10:37
 * @description
 */
public class SolutionI {

    public static void main(String[] args) {
        System.out.println(new SolutionI().splitString("10"));
        System.out.println(Integer.parseInt("0080"));
    }

    public boolean splitString(String s) {
        int zero = 0;
        while (zero < s.length() && s.charAt(zero) == '0') {
            zero++;
        }
        s = s.substring(zero);
        if (s.length() <= 1) {
            return false;
        }
        int halfLength = s.length() / 2 + 1;
        String targetStr;
        lable:
        for (int i = 1; i <= halfLength; i++) {
            if (i == s.length()) {
                return false;
            }
            String firstStr = s.substring(0, i);
            targetStr = String.valueOf(Long.parseLong(firstStr) - 1);
            int idx = i;
            while (idx < s.length()) {
                while (idx < s.length() && s.charAt(idx) == '0') {
                    idx++;
                }
                if (idx >= s.length()) {
                    if (targetStr.equals("0")) {
                        return true;
                    }
                    continue lable;
                }
                for (int j = 0; j < targetStr.length(); j++) {
                    if (idx >= s.length()) {
                        continue lable;
                    }
                    if (targetStr.charAt(j) != s.charAt(idx)) {
                        continue lable;
                    }
                    idx++;
                }
                targetStr = String.valueOf(Long.parseLong(targetStr) - 1);
            }
            if (idx >= s.length()) {
                return true;
            }
        }
        return false;
    }

}
