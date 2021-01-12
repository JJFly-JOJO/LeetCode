package Array.NO163MissingRanges;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zzj
 * @version 1.0
 * @date 2020/11/20 12:40
 * @description
 */
public class Solution {

    public List<String> findMissingRanges(int[] nums, int lower, int upper) {
        List<String> res = new ArrayList<>();
        int len = nums.length + 2;
        int[] arr = new int[len];
        //使用哨兵 减少讨论-------------------------技巧：哨兵
        arr[0] = lower - 1;
        arr[len - 1] = upper + 1;
        System.arraycopy(nums, 0, arr, 1, nums.length);
        for (int i = 1; i < len; i++) {
            if (arr[i] - 1 != arr[i - 1]) {
                int l = arr[i - 1] + 1;
                int r = arr[i] - 1;
                if (l != r) {
                    //String的字符串拼接效率低
                    //res.add(l + "->" + r);
                    //还可以优化 复用Stringbuilder 利用setLength重置（或者delete 效率更高） 减少StringBuilder对象的创建
                    res.add(new StringBuilder().append(l).append("->").append(r).toString());
                } else {
                    res.add(String.valueOf(l));
                }
            }
        }
        return res;
    }
}
