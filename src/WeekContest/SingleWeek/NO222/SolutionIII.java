package WeekContest.SingleWeek.NO222;

/**
 * @author zzj
 * @version 1.0
 * @date 2021/1/3 11:23
 * @description  ---------------二分查找-----------
 * 标准二分查找格式：
 * while(l<=r){
 *     int mid=(l+r)>>>1;
 *     if(nums[mid]>target){
 *         //等号为可选项 如果有等号 那么表明向左移动
 *         r=mid-1;
 *     }else{
 *         l=mid+1;
 *     }
 * }
 * 最终得到的r对应的值是nums[r]<target l对应的值是nums[l]>=target
 *
 */
public class SolutionIII {

    public static void main(String[] args) {
        //System.out.println(Integer.MAX_VALUE);
        System.out.println(new SolutionIII().waysToSplit(new int[]{8892, 2631, 7212, 1188, 6580, 1690, 5950, 7425, 8787, 4361, 9849, 4063, 9496, 9140, 9986, 1058, 2734, 6961, 8855, 2567, 7683, 4770, 40, 850, 72, 2285, 9328, 6794, 8632, 9163, 3928, 6962, 6545, 6920, 926, 8885, 1570, 4454, 6876, 7447, 8264, 3123, 2980, 7276, 470, 8736, 3153, 3924, 3129, 7136, 1739, 1354, 661, 1309, 6231, 9890, 58, 4623, 3555, 3100, 3437}));
    }

    public int waysToSplit(int[] nums) {
        int mod = (int) 1e9 + 7;
        int len = nums.length;
        int[] subSum = new int[len];
        long count = 0L;
        subSum[0] = nums[0];
        for (int i = 1; i < len; i++) {
            subSum[i] = subSum[i - 1] + nums[i];
        }
        int left = 0;
        while (left < len - 2) {
            if (3 * subSum[left] > subSum[len - 1]) {
                break;
            }
            //第一次二分找mid的左边界
            int l = left + 1;
            int r = len - 1;
            int target = subSum[left] << 1;
            while (l <= r) {
                int mid = (l + r) >>> 1;
                if (subSum[mid] >= target) {
                    r = mid - 1;
                } else {
                    l = mid + 1;
                }
            }

            //第二次二分找mid的右边界
            int ll = left + 1;
            int rr = len - 1;
            target = (subSum[left] + subSum[rr]) >>> 1;
            while (ll <= rr) {
                int mid = (ll + rr) >>> 1;
                if (subSum[mid] > target) {
                    rr = mid - 1;
                } else {
                    //=target
                    ll = mid + 1;
                }
            }
//            if (rr < l) {
//                break;
//            }
            count += rr - l + 1;
            count %= mod;
            left++;
        }
        return (int) count % mod;
    }


}
