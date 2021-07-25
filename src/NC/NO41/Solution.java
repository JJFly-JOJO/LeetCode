package NC.NO41;

import java.util.HashMap;
import java.util.Map;

/**
 * @author zzj
 * @version 1.0
 * @date 2021/6/16 22:12
 * @description
 */
public class Solution {

    public int maxLength(int[] arr) {
        // write code here
        int ans = 0;
        int l = 0;
        int r = 0;
        int len = arr.length;
        Map<Integer, Integer> cntMap = new HashMap<>();
        while (r < len) {
            if (cntMap.getOrDefault(arr[r], 0) >= 1) {
                ans = Math.max(r - l, ans);
                cntMap.put(arr[l], cntMap.get(arr[l]) - 1);
                l++;
            } else {
                cntMap.put(arr[r], cntMap.getOrDefault(arr[r], 0) + 1);
                r++;
            }
        }
        ans = Math.max(ans, r - l);
        return ans;
    }

}
