package Math.NO263UglyNumber;

/**
 * @author zzj
 * @version 1.0
 * @date 2020/12/24 11:02
 * @description
 */
public class Solution {

    public boolean isUgly(int num) {
        if (num == 0) {
            return false;
        }
        while (num != 1) {
            if (num % 5 == 0) {
                num /= 5;
                continue;
            }
            if (num % 3 == 0) {
                num /= 3;
                continue;
            }
            if (num % 2 == 0) {
                num /= 2;
                continue;
            }
            return false;
        }
        return true;
    }

}
