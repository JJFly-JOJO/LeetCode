package String.NO68TextJustification;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zzj
 * @version 1.0
 * @date 2020/9/17 15:16
 */
public class Solution {

    public static void main(String[] args) {
        String[] words = new String[]{"What", "must", "be", "acknowledgment", "shall", "be"};
        for (String temp : new Solution().fullJustify(words, 16)) {
            System.out.println(temp);
        }
    }

    public List<String> fullJustify(String[] words, int maxWidth) {
        int length = words.length;
        List<String> res = new ArrayList<>();
        List<Integer> curRowResIndex = new ArrayList<>();
        StringBuilder tempRes = new StringBuilder();
        for (int i = 0; i < length; ) {
            int row = maxWidth;
            int countSpace = maxWidth;
            do {
                int templength = words[i].length();
                countSpace -= templength;
                row -= templength + 1;
                curRowResIndex.add(i++);
            } while (i < length && row >= words[i].length());
            //拼接结果 注意要判断是否到达最后一行
            if (curRowResIndex.get(curRowResIndex.size() - 1) == length - 1) {
                //最后一行
                //计算最后要append的空格数量
                countSpace = countSpace - curRowResIndex.size();
                for (int k = 0; k < curRowResIndex.size(); k++) {
                    tempRes.append(words[curRowResIndex.get(k)]).append(" ");
                }
                if (countSpace < 0) {
                    tempRes.deleteCharAt(tempRes.length() - 1);
                } else {
                    for (int b = 0; b < countSpace; b++) {
                        tempRes.append(" ");
                    }
                }
            } else {
                //计算平均要加的空格数量 以及前面要多加的空格数量
                int addMore = 0;
                if (curRowResIndex.size() > 1) {
                    addMore = countSpace % (curRowResIndex.size() - 1);
                    countSpace /= curRowResIndex.size() - 1;
                }
                for (int k = 0; k < curRowResIndex.size(); k++) {
                    tempRes.append(words[curRowResIndex.get(k)]);
                    for (int b = 0; b < countSpace; b++) {
                        tempRes.append(" ");
                    }
                    if (addMore-- > 0) {
                        tempRes.append(" ");
                    }
                }
                //删除最后一个多的加的空格
                //如果当前行只有一个单词 则需要单独拿出来讨论
                if (curRowResIndex.size() > 1) {
                    tempRes.delete(tempRes.length() - countSpace, tempRes.length());
                }
            }
            res.add(tempRes.toString());
            tempRes.delete(0, tempRes.length());
            curRowResIndex.clear();
        }
        return res;
    }

}
