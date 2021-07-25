package WeekContest.SingleWeek.NO243;

/**
 * @author zzj
 * @version 1.0
 * @date 2021/5/30 10:35
 * @description
 */
public class SolutionI {

    public boolean isSumEqual(String firstWord, String secondWord, String targetWord) {
        int first = transfer(firstWord);
        int second = transfer(secondWord);
        int third = transfer(targetWord);
        return first + second == third;
    }

    private int transfer(String firstWord) {
        char[] chars = firstWord.toCharArray();
        int val = 0;
        for (int i = 0; i < chars.length; i++) {
            val = val * 10 + (chars[i] - 'a');
        }
        return val;
    }

}
