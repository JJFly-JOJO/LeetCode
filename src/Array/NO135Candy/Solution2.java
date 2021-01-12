package Array.NO135Candy;

import java.util.Arrays;

/**
 * @author zzj
 * @version 1.0
 * @date 2020/10/10 15:10
 * --------------------------方法二 两个数组 分别记录当前元素与左侧元素比较 以及当前元素与右侧比较的结果----------
 * --------------------------方法三 一个数组 在方法二上改进------------------------
 */
public class Solution2 {

    public int candy(int[] ratings) {
        int length = ratings.length;
        int[] curToLeft = new int[length];
        Arrays.fill(curToLeft, 1);
        int[] curToRight = new int[length];
        Arrays.fill(curToRight, 1);
        //第一次循环 当前元素比左侧元素的rating高 则加1
        for (int i = 1; i < length; i++) {
            if (ratings[i] > ratings[i - 1]) {
                curToLeft[i] = curToLeft[i - 1] + 1;
            }
        }
        //第二次循环 当前元素比右侧元素rating高 则加1
        for (int i = length - 2; i >= 0; i--) {
            if (ratings[i] > ratings[i + 1]) {
                curToRight[i] = curToRight[i + 1] + 1;
            }
        }
        //比较两个数组 每个index下取值最大的元素作为该同学所获得的糖果
        for (int i = 0; i < length; i++) {
            curToLeft[i] = Math.max(curToLeft[i], curToRight[i]);
        }
        int sum = 0;
        for (int temp : curToLeft) {
            sum += temp;
        }
        return sum;
    }

    public int candy2(int[] ratings) {
        int length = ratings.length;
        int[] candies = new int[length];
        Arrays.fill(candies, 1);
        for (int i = 1; i < length; i++) {
            if (ratings[i] > ratings[i - 1]) {
                candies[i] = candies[i - 1] + 1;
            }
        }
        for (int i = length - 2; i >= 0; i--) {
            if (ratings[i] > ratings[i + 1] && candies[i] <= candies[i + 1]) {
                candies[i] = candies[i + 1] + 1;
            }
        }
        int sum = 0;
        for (int temp : candies) {
            sum += temp;
        }
        return sum;
    }
}
