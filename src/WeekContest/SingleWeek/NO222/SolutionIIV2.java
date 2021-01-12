package WeekContest.SingleWeek.NO222;

import java.util.HashMap;
import java.util.Map;

/**
 * @author zzj
 * @version 1.0
 * @date 2021/1/3 17:05
 * @description -----------类似两数之和哈希表的解法------------
 * 思路：这里针对2的幂 我们利用题干条件的元素最大值2的20次方枚举1到2的20次方，看哈希表中是否存在另一个值匹配
 */
public class SolutionIIV2 {

    public static void main(String[] args) {
        System.out.println(Math.pow(2, 21));
    }

    public int countPairs(int[] deliciousness) {
        //key->num value->count
        Map<Integer, Integer> map = new HashMap<>();
        int mod = (int) 1e9 + 7;
        int res = 0;
        for (int d : deliciousness) {
            int num = 1;
            //题干 元素最大值为2的20次方
            for (int i = 1; i <= 22; i++) {
                if (num >= d && map.containsKey(num - d)) {
                    res += map.get(num - d);
                    res %= mod;
                }
                num *= 2;
            }
            map.put(d, map.getOrDefault(d, 0) + 1);
        }
        return res;
    }
}
