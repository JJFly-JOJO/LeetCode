package WeekContest.SingleWeek.NO207;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zzj
 * @version 1.0
 * @date 2020/9/20 10:33
 */
public class Solution {

    public static void main(String[] args) {
        String s = "  this   is  a sentence ";
        //String[] strs = s.split(" ");
        //int a = 0;
        System.out.println(new Solution().reorderSpaces(s));
    }

    public String reorderSpaces(String text) {
        int spaceCount=text.length();
        StringBuilder res = new StringBuilder();
        String[] strs = text.split(" ");
        //int spaceCount = 0;
        List<String> strsArray = new ArrayList<>();
        for (int i = 0; i < strs.length; i++) {
            if (strs[i].length() != 0) {
                strsArray.add(strs[i]);
                spaceCount-=strs[i].length();
            }
        }
        int wordsCount = strsArray.size();
        if (wordsCount == 1) {
            res.append(strsArray.get(0));
            for (int i = 0; i < spaceCount; i++) {
                res.append(" ");
            }
            return res.toString();
        }
        int spaces = spaceCount / (wordsCount - 1);
        int lastSpace = spaceCount % (wordsCount - 1);
        for (int i = 0; i < wordsCount - 1; i++) {
            res.append(strsArray.get(i));
            for (int b = 0; b < spaces; b++) {
                res.append(" ");
            }
        }
        res.append(strsArray.get(wordsCount - 1));
        for (int i = 0; i < lastSpace; i++) {
            res.append(" ");
        }
        return res.toString();
    }

}
