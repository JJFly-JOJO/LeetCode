package Array.NO134GasStation;

public class Solution {
    public static void main(String[] args) {
        int[] gas = new int[]{5, 1, 2, 3, 4};
        int[] cost = new int[]{4, 4, 1, 5, 1};
        System.out.println(new Solution().canCompleteCircuit(gas, cost));
    }

    public int canCompleteCircuit(int[] gas, int[] cost) {
        int n = gas.length;
        //重构数组 gas和cost和并
        int[] node = new int[n];
        for (int i = 0; i < n; i++) {
            node[i] = gas[i] - cost[i];
        }
        int negative = 0;
        //int positive = 0;
        int curSum = 0;
        int start = 0;
        for (int i = 0; i < n; i++) {
            int judge = curSum + node[i];
            if (judge < 0) {
                negative = negative + judge;
                //下一步油不够 归零 从新找start点
                curSum = 0;
            } else if (judge >= 0) {
                if (curSum == 0) {
                    //第一步满足条件
                    start = i;
                }
                curSum = judge;
            }
        }
        if (curSum + negative >= 0) {
            return start;
        }
        return -1;
    }
}
