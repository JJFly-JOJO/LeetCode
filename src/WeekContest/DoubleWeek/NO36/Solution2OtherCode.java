package WeekContest.DoubleWeek.NO36;

import java.util.*;

/**
 * @author zzj
 * @version 1.0
 * @date 2020/10/6 11:09
 */
public class Solution2OtherCode {
    public List<String> alertNames(String[] keyName, String[] keyTime) {
        Map<String, List<String>> hash = new HashMap<>();
        //Set<String> set = new TreeSet<>();
        List<String> set = new ArrayList<>();
        for (int i = 0; i < keyName.length; i++) {
            if (hash.containsKey(keyName[i]))
                hash.get(keyName[i]).add(keyTime[i]);
            else {
                List<String> tList = new ArrayList<>();
                tList.add(keyTime[i]);
                hash.put(keyName[i], tList);
            }
        }

        hash.forEach((k, v) -> {
            int[] t = new int[v.size()];
            for (int i = 0; i < v.size(); i++) {
                char[] c = v.get(i).toCharArray();
                t[i] = (c[0] - '0') * 600 + (c[1] - '0') * 60 + (c[3] - '0') * 10 + (c[4] - '0');
            }
            Arrays.sort(t);
            for (int i = 0; i + 2 < t.length; i++) {
                if (t[i + 2] - t[i] <= 60) {
                    set.add(k);
                    break;
                }
            }
        });

        //List<String> ans = new ArrayList<>();
        Collections.sort(set, String::compareTo);
        return set;
    }
}
