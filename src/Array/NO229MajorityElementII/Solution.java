package Array.NO229MajorityElementII;

import java.util.ArrayList;
import java.util.List;

public class Solution {
    public static void main(String[] args) {
        int[] nums = new int[]{1, 2, 3, 4};
        for (Integer temp : new Solution().majorityElement(nums)) {
            System.out.println(temp);
        }
    }


    /**
     * 注意题干的下取整
     *
     * @param nums
     * @return
     */
    public List<Integer> majorityElement(int[] nums) {
        List<Integer> res = new ArrayList<>();
        int length = nums.length;
        int candidate1 = Integer.MAX_VALUE;
        int candidate2 = Integer.MAX_VALUE;
        int num1 = 0;
        int num2 = 0;
        for (int i = 0; i < length; i++) {
            if (nums[i] == candidate1) {
                num1 = num1 + 1;
            } else if (nums[i] == candidate2) {
                num2 = num2 + 1;
            } else {
                if (num1 == 0) {
                    //更新候选人
                    candidate1 = nums[i];
                    num1 = num1 + 1;
                    continue;
                } else {
                    if (num2 != 0) {
                        //如果num2是0 那么当前的nums[i]不是用来减去num1的 而是给num2重新赋值的
                        num1 = num1 - 1;
                    }
                }
                if (num2 == 0) {
                    candidate2 = nums[i];
                    num2 = num2 + 1;
                } else {
                    num2 = num2 - 1;
                }
            }
        }
        //重新遍历 检查个数是否满足要求
        //通过moore算法我们只能缩小要找的元素范围
        num1 = 0;
        num2 = 0;
        for (int temp : nums) {
            if (temp == candidate1) {
                num1 = num1 + 1;
            } else if (temp == candidate2) {
                num2 = num2 + 1;
            }
        }

        /*int aver = (length - num1 - num2) / 3;
        num1 = aver + num1;
        num2 = aver + num2;*/
        if (num1 > length / 3) {
            res.add(candidate1);
        }
        if (num2 > length / 3) {
            res.add(candidate2);
        }
        return res;
    }

}
