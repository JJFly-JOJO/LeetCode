package Array.NO228SummaryRanges;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zzj
 * @version 1.0
 * @date 2020/11/18 11:44
 * @description
 */
public class Solution {

    public List<String> summaryRanges(int[] nums) {
        String arrow = "->";
        int len = nums.length;
        List<String> res = new ArrayList<>();
        if (len == 0) {
            return res;
        }
        if (len == 1) {
            res.add(String.valueOf(nums[0]));
            return res;
        }
        int head = nums[0];
        for (int i = 1; i < len; i++) {
            if (nums[i] - 1 != nums[i - 1]) {
                if (nums[i - 1] != head) {
                    res.add(head + arrow + nums[i - 1]);
                } else {
                    res.add(String.valueOf(head));
                }
                head = nums[i];
            }
        }
        if (nums[len - 1] != head) {
            res.add(head + arrow + nums[len - 1]);
        } else {
            res.add(String.valueOf(head));
        }
        return res;
    }

}
