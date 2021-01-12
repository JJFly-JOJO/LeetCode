package Array.NO330PatchingArray;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author zzj
 * @version 1.0
 * @date 2020/10/10 15:34
 * --------------贪心算法 一定要抓住只考虑当前（局部）最优解 不要把眼光放到全局-------
 * 输入: nums = [1,3], n = 6
 * 输出: 1
 * 解释:
 * 根据 nums 里现有的组合 [1], [3], [1,3]，可以得出 1, 3, 4。
 * 现在如果我们将 2 添加到 nums 中， 组合变为: [1], [2], [3], [1,3], [2,3], [1,2,3]。
 * 其和可以表示数字 1, 2, 3, 4, 5, 6，能够覆盖 [1, 6] 区间里所有的数。
 * 所以我们最少需要添加一个数字。
 * <p>
 * 输入: nums = [1,5,10], n = 20
 * 输出: 2
 * 解释: 我们需要添加 [2, 4]
 * --------------------------笔记------------------------
 * 贪心思路：
 */
public class Solution {

    /**
     * 该方法不可取 java定义数组长度是不能超过long（Integer.MAX_VALUE）因为32位机的最大寻址空间是2的32次方（4G）
     * 实际是没法实现的
     *
     * @param nums
     * @param n
     * @return
     */
    public int minPatchesErro(int[] nums, int n) {
        int addCount = 0;
        Set<Integer> numSet = new HashSet<>();
        boolean[] isContain = new boolean[n + 1];
        //initialize
        for (int num : nums) {
            List<Integer> tempList = new ArrayList<>();
            for (int temp : numSet) {
                int val = temp + num;
                if (val <= n && !numSet.contains(val)) {
                    tempList.add(val);
                    isContain[val] = true;
                }
            }
            numSet.addAll(tempList);
            if (!numSet.contains(num)) {

                numSet.add(num);
                isContain[num] = true;
            }
        }
        //
        for (int i = 1; i <= n; i++) {
            if (isContain[i]) {
                continue;
            }
            //当前元素不存在 则需要加入
            addCount++;
            List<Integer> tempList = new ArrayList<>();
            for (int temp : numSet) {
                int val = temp + i;
                if (val <= n && !numSet.contains(val)) {
                    tempList.add(val);
                    isContain[val] = true;
                }
            }
            numSet.addAll(tempList);
            numSet.add(i);
            isContain[i] = true;
        }
        return addCount;
    }

    /**
     * 贪心算法-----------我们首先要清楚 一段连续的整数所能表达的整数范围：1~n能够表达的整数范围为1~(1+2+3+...+n)
     *
     * @param nums
     * @param n
     * @return
     */
    public int minPatches1(int[] nums, int n) {
        int addCount = 0;
        int length = nums.length;
        //设置遍历集合
        Set<Long> isBeing = new HashSet<>();
        for (long temp : nums) {
            isBeing.add(temp);
        }
        //max表示当前能够表示到的最大整数 对比下面写法的miss 需要达到的最小一个整数
        long max = 0L;
        int seeIndex = 0;
        while (max < n) {
            int sum = 0;
            for (; seeIndex < length; seeIndex++) {
                if (nums[seeIndex] > max) {
                    break;
                }
                sum += nums[seeIndex];
            }
            if (sum != 0) {
                max += sum;
                continue;
            }
            long nextNum = max + 1;
            if (isBeing.contains(nextNum)) {
                max += nums[seeIndex++];
            } else {
                addCount++;
                max += nextNum;
            }
        }
        return addCount;
    }

    /**
     * 贪心算法
     * 简化写法
     *
     * @param nums
     * @param n
     * @return
     */
    public int minPatches(int[] nums, int n) {
        int patches = 0;
        //当前miss的右边界 long防止溢出
        long miss = 1;
        //用来记录当前nums所到的下标
        int index = 0;
        while (miss <= n) {
            //注意这里 如果有nums[i]能够满足加上能够覆盖miss 那么我们就使用nums 而不是加入一个miss值
            //这也体现了贪心的思想 优先使用当前存在的数
            if (index < nums.length && nums[index] <= miss) {
                miss += nums[index++];
            } else {
                patches++;
                miss += miss;
            }
        }
        return patches;
    }

}
