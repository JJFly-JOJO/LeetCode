package String.NO293FlipGame;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zzj
 * @version 1.0
 * @date 2020/11/6 14:57
 * @description
 */
public class Solution {

    public List<String> generatePossibleNextMoves(String s) {
        List<String> res = new ArrayList<>();
        char[] chars = s.toCharArray();
        int last = chars.length - 1;
        for (int i = 0; i < last; i++) {
            if (chars[i] == '+' && chars[i + 1] == '+') {
                chars[i] = '-';
                chars[i + 1] = '-';
                res.add(new String(chars));
                chars[i] = '+';
                chars[i + 1] = '+';
            }
        }
        return res;
    }

}
