package WeekContest.SingleWeek.NO214;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @author zzj
 * @version 1.0
 * @date 2020/11/8 10:29
 * @description
 */
public class Solution2 {

    public static void main(String[] args) {
        System.out.println(new Solution2().minDeletions("aab"));
    }

    public int minDeletions(String s) {
        char[] chars = s.toCharArray();
        int[] map = new int[26];
        for (char c : chars) {
            map[c - 'a']++;
        }
        Set<Integer> count = new HashSet<>();
        Map<Integer, Integer> countToNum = new HashMap<>();
        for (int i : map) {
            count.add(i);
            countToNum.put(i, countToNum.getOrDefault(i, 0) + 1);
        }
        int res = 0;
        for (Map.Entry<Integer, Integer> e : countToNum.entrySet()) {
            int key = e.getKey();
            if(key==0){
                continue;
            }
            int val = e.getValue();
            while (val > 1) {
                int temp = key - 1;
                while (temp > 0 && count.contains(temp)) {
                    temp--;
                }
                count.add(temp);
                res += key - temp;
                val--;
            }
        }
        return res;
    }

}
