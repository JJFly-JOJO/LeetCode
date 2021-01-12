package Math.NO1703MinimumAdjacentSwapsforKConsecutiveOnes;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zzj
 * @version 1.0
 * @date 2020/12/29 10:06
 * @description ------------从暴力方法中找到思路-----------
 * 思路：对于暴力解法，枚举所有可能的连续k区间，从元素集中枚举所有k元素的情况，计算这k元素移动到这k区间所需要的步数
 * 优化1：k个元素移动到k区间，一定是第一个元素移动到k区间的第一个位置，第二个元素移动到k区间的第二个位置，不会存在交叉移动的情况，因为这样一定会走更多步数
 * 优化2：从元素集中取出的元素也一定是连续的，也就是如果取出两个元素，不会取下标i和下标i+n，应该取下标i和i+1
 * 思考：我们能否只用枚举取k元素的可能，取出k元素之后就能确定哪一个连续的k区间是该k元素移动到k区间的最小距离（当然不用关注连续k区间的位置，只要能确定最小距离即可）
 */
public class Solution {

    public static void main(String[] args) {
        int a = 5;
        a /= 2;
        System.out.println(a);
    }

    public int minMoves(int[] nums, int k) {
        int res = Integer.MAX_VALUE;
        List<Integer> g = new ArrayList<>();
        List<Integer> subSum = new ArrayList<>();
        //subSum记录的个数 前0个 前1个 ...前n个元素和
        subSum.add(0);
        int count = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 1) {
                g.add(i - count++);
                subSum.add(subSum.get(subSum.size() - 1) + g.get(g.size() - 1));
            }
        }
        //slide window
        int gSize = g.size();
        for (int i = 0; i <= gSize - k; i++) {
            int last = i + k - 1;
            int mid = (i + last) / 2;
            int gMid = g.get(mid);
            int sum = (2 * (mid - i) - k + 1) * gMid - (subSum.get(mid) - subSum.get(i)) + (subSum.get(last + 1) - subSum.get(mid + 1));
            res = Math.min(res, sum);
        }
        return res;
    }

}
