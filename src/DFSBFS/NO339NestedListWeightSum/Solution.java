package DFSBFS.NO339NestedListWeightSum;

import java.util.List;

/**
 * @author zzj
 * @version 1.0
 * @date 2020/10/31 11:53
 * @description
 */
public class Solution {

    private int sum = 0;

    public int depthSum(List<NestedInteger> nestedList) {
        for (NestedInteger t : nestedList) {
            dfs(t, 1);
        }
        return sum;
    }

    private void dfs(NestedInteger t, int depth) {
        if (t.isInteger()) {
            sum += depth * t.getInteger();
            return;
        }
        for (NestedInteger temp : t.getList()) {
            dfs(temp, depth + 1);
        }
    }

}
