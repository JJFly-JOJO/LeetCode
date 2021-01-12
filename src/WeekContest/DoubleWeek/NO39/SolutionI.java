package WeekContest.DoubleWeek.NO39;

/**
 * @author zzj
 * @version 1.0
 * @date 2020/11/14 22:49
 * @description
 */
public class SolutionI {

    public int[] decrypt(int[] code, int k) {
        int len = code.length;
        int[] arr = new int[len];
        if (k == 0) {
            return arr;
        }
        boolean neg = false;
        if (k < 0) {
            neg = true;
            k = -k;
        }
        for (int i = 0; i < len; i++) {
            int count = 1;
            int t = 0;
            if (neg) {
                while (count <= k) {
                    int index = i - count;
                    if (index < 0) {
                        t += code[index + len];
                    } else {
                        t += code[index];
                    }
                    count++;
                }
            } else {
                while (count <= k) {
                    t += code[(i + count) % len];
                    count++;
                }
            }
            arr[i] = t;
        }
        return arr;
    }

}
