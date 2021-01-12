package Array.NO135Candy;

import java.util.Arrays;

/**
 * @author zzj
 * @version 1.0
 * @date 2020/10/9 16:49
 * <p>
 * 每个孩子至少分配到 1 个糖果。
 * 相邻的孩子中，评分高的孩子必须获得更多的糖果。
 * <p>
 * 输入: [1,0,2]
 * 输出: 5
 * 解释: 你可以分别给这三个孩子分发 2、1、2 颗糖果。
 * <p>
 * 输入: [1,2,2]
 * 输出: 4
 * 解释: 你可以分别给这三个孩子分发 1、2、1 颗糖果。
 * 第三个孩子只得到 1 颗糖果，这已满足上述两个条件。
 */
public class Solution {

    public int candyErro(int[] ratings) {
        int min = 1;
        int sum = 1;
        int start = 1;
        int startIndex = 0;
        int length = ratings.length;
        for (int i = 1; i < length; ) {
            if (ratings[i] > ratings[i - 1]) {
                start++;
            } else if (ratings[i] < ratings[i - 1]) {
                start--;
                min = Math.min(min, start);
            } else {
                //当前i元素等于i-1元素 另外讨论
                int left = i - 1;
                while (i < length && ratings[i] == ratings[i - 1]) {
                    i++;
                }
                int right = i - 1;
                sum += (left - startIndex + 1) * (1 - min) + (right - left - 1);
                startIndex = right;
                start = 1;
                i = right;
                min = 1;
            }
            sum += start;
            i++;
        }
        return sum + (length - startIndex) * (1 - min);
    }

    /**
     * ------------------方法一 暴力法 不停地迭代 每次迭代与左右元素相比较
     *     如果比左边或者右边元素大 那么就更新当前人所持的candy个数（+1）-----------
     *
     * @param ratings
     * @return
     */
    public int candy(int[] ratings) {
        int length = ratings.length;
        //每个人手持的糖果
        int[] candies = new int[length];
        Arrays.fill(candies, 1);
        //用来判断是否需要继续迭代更新
        boolean flag = true;
        while (flag) {
            flag = false;
            for (int i = 0; i < length; i++) {
                //更新当前元素与左侧元素比较
                if (i > 0 && ratings[i] > ratings[i - 1] && candies[i] <= candies[i - 1]) {
                    candies[i] = candies[i - 1] + 1;
                    flag = true;
                }
            }
            for (int i = 0; i < length; i++) {
                //更新当前元素与右侧元素比较
                if (i < length - 1 && ratings[i] > ratings[i + 1] && candies[i] <= candies[i + 1]) {
                    candies[i] = candies[i + 1] + 1;
                    flag = true;
                }
            }
        }
        int sum = 0;
        for (int temp : candies) {
            sum += temp;
        }
        return sum;
    }


}
