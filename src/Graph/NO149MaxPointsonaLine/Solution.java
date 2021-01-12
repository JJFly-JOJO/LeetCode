package Graph.NO149MaxPointsonaLine;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 *
 * -----------------------要考虑代价问题 有时候重复几次搜索往往快于循环内的几次计算------------------------------
 *
 * 思路：
 * 确定一条直线 可以通过k b确定。但是，计算机浮点数计算存在误差 如果用kb放入hash表中 来判断是否是同一条直线
 * 会存在误差，同时kb的计算也会消耗时间。一条直线还可以通过点和k来确定 减少了b的计算
 * 如何找共线点-----------暴力遍历
 * 通过i=0 j=i+1遍历所有点集 找到每两个点构建的直线 相同直线放在一个集合中
 * 对于外层i 是点与k确定直线的点 对于内层 是每个点与外层i点组成的斜率 这样 点加斜率就组成了唯一直线
 * 求出每条直线对应的点集数 找到最多点数 即为结果
 * 加快搜索：
 * 对于过当前i点的直线 j层循环只要满足斜率相同 就说明这个点在当前的直线上 那么可以加入hashmap中
 * 是否会出现相同的直线出现两次（i层循环的时候）的问题？
 * 这个是不可能出现的 注意到我们两两点集组合  j=i+1，也就是说 当前层i=a j=a+b遍历到了后 i+1....之后 都不会再出现i=a+b j=a的情况
 * 自然是不会出现重复相同直线的问题
 * 斜率浮点数计算问题：
 * 我们可以用分数表示斜率 同时对斜率分数要进行约分
 */
public class Solution {

    public static void main(String[] args) {
        System.out.println(new Solution().getCommonDivisor(3, 10));
    }

    /**
     * @param points
     * @return
     */
    public int maxPoints(int[][] points) {
        int pointsNum = points.length;
        if (pointsNum < 3) {
            return pointsNum;
        }
        int maxMax = 0;
        Set<String> isVisited = new HashSet<>();
        Map<String, Integer> linePointsNum = new HashMap<>();
        for (int i = 0; i < pointsNum; i++) {
            //斜率由于用分母表达 我们可以转换为分母+@+分子的字符串作为key
            int max = 0;
            int dupicatePoint = 0;
            for (int j = i + 1; j < pointsNum; j++) {
                //数据存在重复点的情况 如果点在i层点上 我们记录下重复数值 最后得到的直线上点的数量时加上这个点就可以了
                int y = points[j][1] - points[i][1];
                int x = points[j][0] - points[i][0];
                if (x == 0 && y == 0) {
                    dupicatePoint++;
                    continue;
                }

                int cd = getCommonDivisor(y, x);
                //斜率k
                y = y / cd;
                x = x / cd;
                //常数b 同样也用分数表示
                int b1 = -points[j][0] * y + points[j][1] * x;
                int b2 = x;
                if (b1 != 0 || b2 != 0) {
                    cd = getCommonDivisor(b1, b2);
                    b1 = b1 / cd;
                    b2 = b2 / cd;
                }
                //key
                String key = y + "@" + x + "@" + b1 + "@" + b2;
                //第一次遍历
                if (isVisited.contains(key)) {
                    continue;
                }
                Integer temp = linePointsNum.getOrDefault(key, 0) + 1;
                linePointsNum.put(key, temp);
                max = Math.max(max, temp);
            }
            for (String temp : linePointsNum.keySet()) {
                isVisited.add(temp);
            }
            maxMax = Math.max(max + dupicatePoint + 1, maxMax);
        }
        return maxMax;

    }

    /**
     * 求最大公约数!!!!!!!!!!!!!!!!!!!!!!!!!
     * 一定要保证被除数不能为0 0与所有数的最大公约数是这个数本身（默认）
     *
     * @param a
     * @param b
     * @return
     */
    private int getCommonDivisor(int a, int b) {
        while (b != 0) {
            int temp = a % b;
            a = b;
            b = temp;
        }
        return a;
    }

}
