package String.NO247StrobogrammaticNumberII;

import javafx.util.Pair;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zzj
 * @version 1.0
 * @date 2020/10/29 14:53
 * @description
 */
public class Solution {

    private List<Pair<Character, Character>> pairs;

    {
        pairs = new ArrayList<>();
        pairs.add(new Pair<>('0', '0'));
        pairs.add(new Pair<>('1', '1'));
        pairs.add(new Pair<>('8', '8'));
        pairs.add(new Pair<>('6', '9'));
        pairs.add(new Pair<>('9', '6'));
    }

    public List<String> findStrobogrammatic(int n) {
        List<String> res = new ArrayList<>();
        //特判
        if (n == 1) {
            res.add("0");
            res.add("1");
            res.add("8");
            return res;
        }
        char[] chars = new char[n];
        for (int i = 1; i < 5; i++) {
            recursive(0, n - 1, pairs.get(i), chars, res);
        }
        return res;
    }

    private void recursive(int l, int r, Pair<Character, Character> p, char[] chars, List<String> res) {
        if (l == r && (p.getKey() == '6' || p.getKey() == '9')) {
            return;
        }
        chars[l] = p.getKey();
        chars[r] = p.getValue();
        if (r - l <= 1) {
            res.add(new String(chars));
            return;
        }
        for (Pair<Character, Character> tp : pairs) {
            recursive(l + 1, r - 1, tp, chars, res);
        }
    }

}
