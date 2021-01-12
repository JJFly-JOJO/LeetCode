package Array.NO55JumpGame;

/**
 * @author zzj
 * @version 1.0
 * @date 2020/9/9 20:55
 */
public class Solution {

    /**
     * 此题也可以直接用贪心算法 直接遍历一遍数组 计算当前元素加上跳跃的值 看最大的值能否超出lastIndex
     * 证明如果能够到当前位置 那么之前的所有位置都能走到
     * 证明方法---反证法 如果存在有一个位置无法到达 那么这个位置之前的所有元素（从startIndex开始）都最多只能跳
     * 到该元素的前一个元素，这与能够跳到后面的元素矛盾
     * <p>
     * 该算法弊端 如果0很多的话 复杂度将是n平方-------每跳到0处 就要遍历回退 下一次又跳到新的0处 可能会有重复到之前0的遍历元素
     *
     * @param nums
     * @return
     */
    public boolean canJump(int[] nums) {
        //先以最大步数跳 跳到0再回看前面元素是否能够满足越过0
        int length = nums.length;
        int index = nums[0] + 0;
        for (; index < length - 1; ) {
            if (nums[index] == 0) {
                //回退到能够越过0的下标处
                int tempIndex = index - 1;
                while (tempIndex >= 0 && tempIndex + nums[tempIndex] <= index) {
                    //注意这里有个误区：回退到下一个0就认为不可能有越过当前0的元素了
                    //反例：3 100 0 3 2 1 0 可以看出第一个元素为3 直接跳到了下一个3处 再跳到0 此时回退 回退到100处就能满足条件
                    tempIndex--;
                }
                if (tempIndex < 0) {
                    return false;
                }
                index = tempIndex;
            }
            index += nums[index];
        }
        return true;
    }

    /**
     * 贪心算法
     *
     * @param nums
     * @return
     */
    public boolean canJumpForGreedy(int[] nums) {
        int lenghth = nums.length;
        int target = lenghth - 1;
        //当前能到的最大下标
        int tempMax = 0;
        for (int i = 0; i < lenghth; i++) {
            if (i <= tempMax) {
                tempMax = Math.max(tempMax, i + nums[i]);
            }
        }
        if (tempMax >= target) {
            return true;
        }
        return false;
    }

    /**
     * 改进---------不需要max这一次比较 调用API耗时
     * 还可以改进---------从终点向前推进 只要前面的元素的下一条能够达到或者超过target 那么就更新该target为前面的这个元素 少一次比较！！！
     * @param nums
     * @return
     */
    public boolean canJumpForGreedyBetter(int[] nums) {
        int lenghth = nums.length;
        int target = 0;
        for (int i = 0; i < lenghth; i++) {
            int temp = nums[i] + i;
            if (temp > target && i <= target) {
                target = temp;
            }
        }
        return target >= lenghth - 1;
    }

}
