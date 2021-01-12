package Array.NO119PascalsTriangleII;

import java.util.ArrayList;
import java.util.List;

public class Solution {

    /**
     * !!!!!!!!!!!数组操作速度远远优于Arraylist 即使最后将数组依次赋值给Arraylist 数组带来的收益远远高于所消耗的时间
     * 尽量使用原生数据类型 因为装箱拆箱也会消耗时间
     *
     *
     * @param rowIndex
     * @return
     */
    public List<Integer> getRow(int rowIndex) {
        int[] row = new int[rowIndex + 1];
        row[0] = 1;
        for (int i = 1; i <= rowIndex; i++) {
            int lastOne = row[0];
            for (int j = 1; j < i; j++) {
                int temp = row[j];
                row[j] = row[j] + lastOne;
                lastOne = temp;
            }
            row[i] = 1;
        }
        List<Integer> res = new ArrayList<>();
        for (int i = 0; i <= rowIndex; i++) {
            res.add(row[i]);
        }

        return res;
    }

}
