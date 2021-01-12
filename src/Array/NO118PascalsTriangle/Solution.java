package Array.NO118PascalsTriangle;

import java.util.ArrayList;
import java.util.List;

public class Solution {

    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> res = new ArrayList<>();
        if (numRows == 0) {
            return res;
        }
        List<Integer> array = new ArrayList<>();
        array.add(1);
        res.add(array);
        for (int i = 1; i < numRows; i++) {
            //当前行元素个数
            int length = i + 1;
            //遍历行 赋值
            array = new ArrayList<>();
            array.add(1);
            int end = length - 1;
            //上一行
            List<Integer> lastLine = res.get(i - 1);
            for (int j = 1; j < end; j++) {
                array.add(lastLine.get(j - 1) + lastLine.get(j));
            }
            array.add(1);
            res.add(array);
        }
        return res;
    }

}
