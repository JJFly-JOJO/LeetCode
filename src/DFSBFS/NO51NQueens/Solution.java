package DFSBFS.NO51NQueens;

import java.util.*;

public class Solution {

    /**
     * 对角线元素特点：
     * 主对角线元素 行号-列号=常数(包括负数)
     * 次对角线元素 行号+列号=常数
     *
     * @param n
     * @return
     */
    public static final char NOT_QUEEN = '.';
    public static final char QUEEN = 'Q';

    public static void main(String[] args) {
        //List<List<String>> result = new Solution().solveNQueens(4);
        boolean[] boolArray=new boolean[10];
        System.out.println(boolArray[0]);
        //输出：false boolean原生数据默认为false
        //List<Boolean> isVisited = new ArrayList<Boolean>(Arrays.asList(boolArray));//注意这里会报错 因为asList是将
                                                                                    //数组转换为ArrayList Arraylist
                                                                                    //只能接收Boolean类型
        //针对于对象数组 注意区别boolean[]原生数据数组
        //对象数组里 所有的元素都是null
        Boolean[] booleans=new Boolean[10];
        List<Boolean> isVisited = new ArrayList<Boolean>(Arrays.asList(booleans));
        //输出为10 说明booleans的十个null元素是拷贝到了isVisited中
        System.out.println(isVisited.size());
        //输出为11 说明ArrayList是可以add null值的
        isVisited.add(null);
        System.out.println(isVisited.size());
    }

    public List<List<String>> solveNQueens(int n) {
        List<List<String>> result = new ArrayList<>();
        //boolean默认值false
        //List<Boolean> isVisited = new ArrayList<Boolean>(Arrays.asList(new Boolean[n]));
        List<Boolean> isVisited = new ArrayList<Boolean>() {
            {
                for (int i = 0; i < n; i++) {
                    add(false);
                }
            }
        };
        Set<Integer> isVisitedForMainDia = new HashSet<>();
        Set<Integer> isVisitedForSecDia = new HashSet<>();
        for (int i = 0; i < n; i++) {
            //当前DFS的结果集
            List<String> path = new ArrayList<>();
            StringBuilder tempStr = new StringBuilder();
            //记录递归层数 0代表第一层 使用对象引用
            Integer level = 0;
            //生成第一排str
            for (int col = 0; col < i; col++) {
                tempStr.append(NOT_QUEEN);
            }
            tempStr.append(QUEEN);
            for (int col = i + 1; col < n; col++) {
                tempStr.append(NOT_QUEEN);
            }
            //当前行加入结果集
            //StringBuilder的toString方法是产生一个新的String对象
            path.add(tempStr.toString());
            //当前行所在i列设置为遍历
            isVisited.set(i, true);
            //当前所在斜对角元素和设置为遍历
            isVisitedForMainDia.add(level - i);
            isVisitedForSecDia.add(level + i);
            //DFS
            DFS(path, level, isVisited, isVisitedForMainDia, isVisitedForSecDia, n, result);
            //判断level 加入结果集
            if (level == n - 1) {
                result.add(path);
            }
            //还原
            isVisitedForSecDia.remove(level + i);
            isVisitedForMainDia.remove(level - i);
            isVisited.set(i, false);
        }
        return result;
    }

    /**
     * @param path
     * @param level
     * @param isVisited
     * @param isVisitedForMainDia
     * @param isVisitedForSecDia
     * @param n
     * @param result
     */
    private void DFS(List<String> path, Integer level,
                     List<Boolean> isVisited,
                     Set<Integer> isVisitedForMainDia,
                     Set<Integer> isVisitedForSecDia,
                     int n,
                     List<List<String>> result) {
        level++;
        for (int i = 0; i < n; i++) {
            if (isVisited.get(i)
                    || isVisitedForMainDia.contains(level - i)
                    || isVisitedForSecDia.contains(level + i)) {
                continue;
            }
            StringBuilder tempStr = new StringBuilder();
            //生成第一排str
            for (int col = 0; col < i; col++) {
                tempStr.append(NOT_QUEEN);
            }
            tempStr.append(QUEEN);
            for (int col = i + 1; col < n; col++) {
                tempStr.append(NOT_QUEEN);
            }
            path.add(tempStr.toString());
            isVisited.set(i, true);
            isVisitedForMainDia.add(level - i);
            isVisitedForSecDia.add(level + i);
            DFS(path, level, isVisited, isVisitedForMainDia, isVisitedForSecDia, n, result);

            if (level == n - 1) {
                result.add(new ArrayList<>(path));
            }

            //还原
            isVisitedForSecDia.remove(level + i);
            isVisitedForMainDia.remove(level - i);
            isVisited.set(i, false);
            path.remove(path.size() - 1);
        }
        level--;
    }

}
