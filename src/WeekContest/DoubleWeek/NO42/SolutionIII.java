package WeekContest.DoubleWeek.NO42;

import java.util.Arrays;

/**
 * @author zzj
 * @version 1.0
 * @date 2020/12/29 11:13
 * @description
 */
public class SolutionIII {

    public String maximumBinaryString(String binary) {
        char[] chars = binary.toCharArray();
        int l = 0;
        for (; l < chars.length; l++) {
            if (chars[l] == '0') {
                break;
            }
        }
        if (l == chars.length) {
            return binary;
        }
        int r = chars.length - 1;
        for (; r > l; r--) {
            if (chars[r] == '0') {
                break;
            }
        }
        int count = 0;
        for (int i = l; i <= r; i++) {
            if (chars[i] == '0') {
                count++;
            }
        }
        Arrays.fill(chars, '1');
        chars[l + count - 1] = '0';
        return new String(chars);
    }

}
