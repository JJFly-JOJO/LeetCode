package WeekContest.DoubleWeek.NO35;

import java.util.HashMap;
import java.util.Map;

/**
 * @author zzj
 * @version 2.0  ------------------数组前缀和--------------联想字符的公共前缀树问题
 * @date 2020/9/20 17:00
 * 思路：对于数组nums 我们可以遍历一次数组 记录前i个数的和 这样，当我们需要求中间j-i段的数之和的话
 * 我们只需要用前i个数之和sum[i]减去前j个数之和sum[j]----->sum[i]-sum[j]即可
 * 图解：
 * 我们以横线来表示计算的当前前缀之和 可以看到 只需要一次遍历 并且只需要计算n次加法 就能表示所有的子串之和
 * ------
 * --------
 * ----------
 */
public class Solution2V2 {

    public static void main(String[] args) {
        int[] nums = new int[]{1000000000, 1000000000, 1000000000};
        System.out.println(new Solution2V2().minSubarray(nums, 3));
    }

    /**
     * @param nums
     * @param p
     * @return
     */
    public int minSubarray(int[] nums, int p) {
        int length = nums.length;
        long sum = 0L;
        //找到余数
        for (int temp : nums) {
            sum += temp;
        }
        /*if(sum<p){
            return -1;
        }*/
        //思路：如果当前sum不能整除p 存在余数mod 那么我们想要删除子串使得数组之和能够整除p
        // 我们减去的子串之和与整数p取余时 这个余数就一定能够抵消sum除p的余数mod
        int mod = (int) (sum % p);
        if (mod == 0) {
            return 0;
        }
        //利用一个map用来记录前i个数之和与p取余 余数作为key 下标作为value
        Map<Integer, Integer> modToIndex = new HashMap<>();
        modToIndex.put(0, -1);
        //记录数组前缀和
        sum = 0L;
        int res = length;
        for (int i = 0; i < length; i++) {
            sum += nums[i];
            int curMod = (int) (sum % p);
            //思考：如果出现余数相同（key相同）的情况 我们应该保留哪一个下标（value值）
            // 注意到我们是边遍历子串 边判断当前子串与前面的子串相减能否满足mod要求 其实类似于
            //for(i=0){for(j=i+1)}双层嵌套循环 也就是说 如果我们遇到余数相同的情况 我们可以将当前余数对应index值
            //进行替换 因为前一个余数对应的index之前可能组成的最小连续子串我们肯定已经找到了 那么我们就可以更新当前key
            // 对应的value值 （由此我们永远能够保证当前curMod所需要的targetMod 其targetMod对应的下标永远是离curMod最近或者不存在）
            modToIndex.put(curMod, i);
            /*if(curMod==mod){
                return i+1;
            }*/
            //如果当前余数（对应前i个数和）大于等于目标余数 我们则需要找到前j个数的和的余数能够抵消掉这多出来的余数
            // 也就是i-j得到的是一个子串 这个子串的余数正好是mod
            // 如果当前余数小于目标余数 那么 由推导公式：
            // mod-(curMod-targetMod)=0(排除的子串余数是两个前缀串余数相减 当然可以增加p的整数倍)
            //则：targetMod=curMod-mod 如果curMod小于mod 那么 我们可以加上一个p（相当于i子串减j子串时发生的借位） 使得得到的targetMod为正数
            int targetMod = curMod >= mod ? curMod - mod : p + curMod - mod;
            Integer index = null;
            if ((index = modToIndex.get(targetMod)) != null) {
                res = Math.min(res, i - index);
            }
        }
        return res == length ? -1 : res;
    }

}
