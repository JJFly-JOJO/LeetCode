package WeekContest.SingleWeek.NO216;

/**
 * @author zzj
 * @version 1.0
 * @date 2020/11/22 10:17
 * @description
 */
public class SolutionI {

    public boolean arrayStringsAreEqual(String[] word1, String[] word2) {
        StringBuilder sb1 = new StringBuilder();
        for (String s : word1) {
            sb1.append(s);
        }
        StringBuilder sb2 = new StringBuilder();
        for (String s : word2) {
            sb2.append(s);
        }
        return sb1.toString().equals(sb2.toString());
    }

}
