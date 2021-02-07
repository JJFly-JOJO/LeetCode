package WeekContest.SingleWeek.NO227;

/**
 * @author zzj
 * @version 1.0
 * @date 2021/2/7 11:13
 * @description
 */
public class SolutionIII {

    public String largestMerge(String word1, String word2) {
        char[] w1 = word1.toCharArray();
        char[] w2 = word2.toCharArray();
        char[] r = new char[w1.length + w2.length];
        int index1 = 0;
        int index2 = 0;
        int size = 0;
        while (index1 < w1.length && index2 < w2.length) {
            if (w1[index1] > w2[index2]) {
                r[size++] = w1[index1++];
            } else if (w1[index1] < w2[index2]) {
                r[size++] = w2[index2++];
            } else {
                int c1 = index1;
                int c2 = index2;
                while (c1 < w1.length && c2 < w2.length && w1[c1] == w2[c2]) {
                    c1++;
                    c2++;
                }
                if (c1 == w1.length || (c2 < w2.length && w2[c2] > w1[c1])) {
                    r[size++] = w2[index2++];
                } else if (c2 == w2.length || (c1 < w1.length && w1[c1] > w2[c2])) {
                    r[size++] = w1[index1++];
                }
            }
        }
        while (index1 < w1.length) {
            r[size++] = w1[index1++];
        }
        while (index2 < w2.length) {
            r[size++] = w2[index2++];
        }
        return new String(r);
    }

}
