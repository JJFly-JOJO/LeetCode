package Math.NO153Sum;

import java.util.*;

/**
 * @author zzj
 * @version 1.0
 * @date 2020/10/15 16:11
 * @description 给定数组 nums = [-1, 0, 1, 2, -1, -4]，
 * <p>
 * 满足要求的三元组集合为：
 * [
 * [-1, 0, 1],
 * [-1, -1, 2]
 * ]
 * <p>
 * [0,0,0,0]---->[[0,0,0],[0,0,0],[0,0,0]]
 */
public class Solution {

    public static void main(String[] args) {
        int[] nums = new int[]{-1, 0, 1, 2, -1, -4};
        System.out.println(new Solution().threeSum(nums));
        //int[] sub=Arrays.copyOfRange(nums, 0, 3);
        //System.out.println(sub);
        //---------------------------注意!!! Arrays.asList只能接收装箱后的数组 int[]类型是不可以的
        //List<Integer> list = Arrays.asList(nums);
    }

    /**
     * -----------------------方法一 暴力循环  存在超时---------------
     *
     * @param nums
     * @return
     */
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        int length = nums.length;
        if (length < 3) {
            return res;
        }
        //预处理数组 去除重复数为4以上元素 两两组合
        Arrays.sort(nums);
        /*List<Integer> list = new ArrayList<>();
        int last = length - 1;
        for (int i = 0; i < length; i++) {
            int count = 1;
            list.add(nums[i]);
            while (i < last && nums[i] == nums[i + 1]) {
                i++;
                count++;
                if (count <= 3) {
                    list.add(nums[i]);
                }
            }
        }*/
        //-------------暴力循环---------//
        //int size = list.size();
        for (int i = 0; i < length; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            for (int j = i + 1; j < length; j++) {
                if (j > (i + 1) && nums[j] == nums[j - 1]) {
                    continue;
                }
                int temp = nums[i] + nums[j];
                for (int k = j + 1; k < length; k++) {
                    /*if (k > 2 && nums[k] == nums[k - 1]) {
                        continue;
                    }*/
                    if (temp + nums[k] == 0) {
                        List<Integer> tempRes = new ArrayList<>();
                        tempRes.add(nums[i]);
                        tempRes.add(nums[j]);
                        tempRes.add(nums[k]);
                        res.add(tempRes);
                        break;
                    }
                }
            }
        }
        /*length = list.size();
        int[] twoSum = new int[length - 1];
        for (int i = 0; i < last; i++) {
            twoSum[i] = list.get(i) + list.get(i + 1);
        }
        //双指针遍历
        last = length - 2;
        for (int k = 0; k < last; k++) {
            for (int j = 0; j < twoSum.length; j++) {
                if (list.get(k) + twoSum[j] == 0) {
                    List<Integer> temp = new ArrayList<>();
                    temp.add(list.get(k));
                    temp.add(list.get(j));
                    temp.add(list.get(j + 1));
                    res.add(temp);
                }
            }
        }*/
        return res;
    }

    /**
     * 最后一层用hashTable
     * 优化1:当nums[i]>0 已经不需要往后寻找了 （由于数组排序后 后面的必然为正数）
     * 优化2:当nums[i]+nums[j]>0也不需要往后寻找了
     *
     * @param nums
     * @return
     */
    public List<List<Integer>> threeSumForHashTable(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        int length = nums.length;
        if (length < 3) {
            return res;
        }
        Arrays.sort(nums);
        Map<Long, Integer> numToIndex = new HashMap<>();
        for (int i = 0; i < length; i++) {
            numToIndex.put((long) nums[i], i);
        }
        for (int i = 0; i < length; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            if (nums[i] > 0) {
                break;
            }
            for (int j = i + 1; j < length; j++) {
                if (j > (i + 1) && nums[j] == nums[j - 1]) {
                    continue;
                }
                long temp = (long) nums[i] + nums[j];
                if (temp > 0) {
                    break;
                }
                Integer index;
                if ((index = numToIndex.get(-temp)) != null && index > j) {
                    //---------改进写法------//
                    res.add(Arrays.asList(nums[i], nums[j], nums[index]));
                }
            }
        }
        return res;
    }

}
