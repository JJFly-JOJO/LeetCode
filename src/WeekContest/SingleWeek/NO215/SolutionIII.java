package WeekContest.SingleWeek.NO215;

/**
 * @author zzj
 * @version 1.0
 * @date 2020/11/16 15:08
 * @description
 */
public class SolutionIII {

    public static void main(String[] args) {
        System.out.println(Integer.MAX_VALUE);
        System.out.println(new SolutionIII().minOperations(new int[]{1241, 8769, 9151, 3211, 2314, 8007, 3713, 5835, 2176, 8227, 5251, 9229, 904, 1899, 5513, 7878, 8663, 3804, 2685, 3501, 1204, 9742, 2578, 8849, 1120, 4687, 5902, 9929, 6769, 8171, 5150, 1343, 9619, 3973, 3273, 6427, 47, 8701, 2741, 7402, 1412, 2223, 8152, 805, 6726, 9128, 2794, 7137, 6725, 4279, 7200, 5582, 9583, 7443, 6573, 7221, 1423, 4859, 2608, 3772, 7437, 2581, 975, 3893, 9172, 3, 3113, 2978, 9300, 6029, 4958, 229, 4630, 653, 1421, 5512, 5392, 7287, 8643, 4495, 2640, 8047, 7268, 3878, 6010, 8070, 7560, 8931, 76, 6502, 5952, 4871, 5986, 4935, 3015, 8263, 7497, 8153, 384, 1136}, 894887480));
    }

    public int minOperations(int[] nums, int x) {
        int len = nums.length;
        int l = 0;
        int r = len - 1;
        long sum = 0L;
        for (int i : nums) {
            sum += i;
        }
        int res = dfs(l, r, x, nums, 0, sum);
        return res == Integer.MAX_VALUE ? -1 : res;
    }

    private int dfs(int left, int right, int target, int[] nums, int step, long sum) {
        if (target == 0) {
            return step;
        }
        if (sum < target) {
            return Integer.MAX_VALUE;
        }
        if (left == right) {
            return target == nums[left] ? step + 1 : -1;
        }
        if (target < 0) {
            return -1;
        }
        if (nums[left] == nums[right]) {
            int s1 = dfs(left + 1, right, target - nums[left], nums, step + 1, sum - nums[left]);
            int s2 = dfs(left, right - 1, target - nums[right], nums, step + 1, sum - nums[right]);
            if (s1 > 0 && s2 > 0) {
                return Math.min(s1, s2);
            } else {
                return Math.max(s1, s2);
            }
        } else {
            //贪心算法
            int s;
            if (nums[left] > nums[right]) {
                s = dfs(left + 1, right, target - nums[left], nums, step + 1, sum - nums[left]);
                if (s > 0) {
                    return s;
                } else {
                    s = dfs(left, right - 1, target - nums[right], nums, step + 1, sum - nums[right]);
                    if (s > 0) {
                        return s;
                    } else {
                        return -1;
                    }
                }
            } else {
                s = dfs(left, right - 1, target - nums[right], nums, step + 1, sum - nums[right]);
                if (s > 0) {
                    return s;
                } else {
                    s = dfs(left + 1, right, target - nums[left], nums, step + 1, sum - nums[left]);
                    if (s > 0) {
                        return s;
                    } else {
                        return -1;
                    }
                }
            }
        }
    }

}
