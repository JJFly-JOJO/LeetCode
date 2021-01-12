package String.NO168ExcelSheetColumnTitle;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zzj
 * @version 1.0
 * @date 2020/9/16 14:48
 */
public class Solution {

    private char[] map = new char[]{' ', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I'
            , 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};

    public static void main(String[] args) {
        System.out.println(new Solution().convertToTitle(52));
    }

    public String convertToTitle(int n) {
        List<Integer> digit = new ArrayList<>();
        int temp = n;
        while (temp > 26) {
            int val = temp % 26;
            temp = temp / 26;
            if (val == 0) {
                digit.add(26);
                temp--;
            } else {
                digit.add(val);
            }
        }
        if (temp != 0) {
            digit.add(temp);
        }
        StringBuilder res = new StringBuilder();
        for (int i = digit.size() - 1; i >= 0; i--) {
            res.append(map[digit.get(i)]);
        }
        return res.toString();
    }

}
