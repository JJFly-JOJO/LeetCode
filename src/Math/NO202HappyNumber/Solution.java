package Math.NO202HappyNumber;

import java.util.HashSet;
import java.util.Set;

/**
 * @author zzj
 * @version 1.0
 * @date 2020/10/20 22:24
 * @description 输入：19
 * 输出：true
 * 解释：
 * 12 + 92 = 82
 * 82 + 22 = 68
 * 62 + 82 = 100
 * 12 + 02 + 02 = 1
 */
public class Solution {
    public boolean isHappy(int n) {
        Set<Integer> isVisited = new HashSet<>();
        while (!isVisited.contains(n)) {
            isVisited.add(n);
            n = change(n);
        }
        return n == 1;
    }

    private int change(int n) {
        int sum = 0;
        while (n != 0) {
            sum += Math.pow(n % 10, 2);
            n /= 10;
        }
        return sum;
    }
}
