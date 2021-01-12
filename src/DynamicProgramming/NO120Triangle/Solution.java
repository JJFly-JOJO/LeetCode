package DynamicProgramming.NO120Triangle;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solution {

    public static void main(String[] args) {
        List<List<Integer>> triangle = new ArrayList<List<Integer>>() {
            //<>泛型中的值 如果不写 默认是List<Integer> 因此这里如果写Arraylist<Integer>将会报错
            {
                //匿名内部类 继承ArrayList  静态代码块 调用父类add方法
                add(new ArrayList<>(Arrays.asList(-1)));
                add(new ArrayList<>(Arrays.asList(2,3)));
                add(new ArrayList<>(Arrays.asList(1,-1,-3)));

                //add(new ArrayList<>(Arrays.asList(-7)));
                //add(new ArrayList<>(Arrays.asList(-2, 1)));
               /* add(new ArrayList<>(Arrays.asList(-5, -5, 9)));
                add(new ArrayList<>(Arrays.asList(-4, -5, 4, 4)));
                add(new ArrayList<>(Arrays.asList(-6, -6, 2, -1, -5)));
                add(new ArrayList<>(Arrays.asList(3, 7, 8, -3, 7, -9)));*/


                //add(new ArrayList<>(Arrays.asList(4, 1, 8, 3)));
            }
        };
        int result = new Solution().minimumTotal(triangle);
        System.out.println(result);
    }

    //确定边界条件 这里从三角形顶点开始c[0][0]
    //针对解得特殊情况
    //循环遍历 得到每一层当前点的最优解
    //当前点可能的最优解只可能为[i-1][j-1]或者[i-1][j]
    public int minimumTotal(List<List<Integer>> triangle) {
        int n = triangle.size();//row
        int[] cur = new int[triangle.get(n - 1).size()];
        cur[0] = triangle.get(0).get(0);//初始化起点（动态规划的边界值）
        for (int i = 1; i < n; i++) {
            int size = triangle.get(i).size();
            int tempCur = cur[0];//中间值来处理cur[j] 因为cur[j]会重新赋值
            cur[0] = triangle.get(i).get(0) + cur[0];//在三角形左边界 最优解只有[i-1][0]可能
            for (int j = 1; j < size - 1; j++) {//从第三排开始 第三排才有中间数
                int temp = cur[j];
                cur[j] = cur[j] < tempCur ? cur[j] + triangle.get(i).get(j) : tempCur + triangle.get(i).get(j);
                tempCur = temp;
            }
            cur[size - 1] = triangle.get(i).get(size - 1) + tempCur;//在三角形右边界 最优解只有[i-1][size-1]可能
        }
        int minTotal = cur[0];
        for (int temp :
                cur) {
            minTotal = Math.min(minTotal, temp);

        }
        return minTotal;//返回最后一行的最小值
    }
}
