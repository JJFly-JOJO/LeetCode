package WeekContest.SingleWeek.NO218;

/**
 * @author zzj
 * @version 1.0
 * @date 2020/12/6 10:34
 * @description
 */
public class SolutionI {

    public String interpret(String command) {
        char[] chars = command.toCharArray();
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] == 'G') {
                res.append('G');
            } else if (chars[i] == ')') {
                if (chars[i - 1] == '(') {
                    res.append('o');
                } else {
                    res.append("al");
                }
            }
        }
        return res.toString();
    }

}
