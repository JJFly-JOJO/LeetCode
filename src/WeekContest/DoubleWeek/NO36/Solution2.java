package WeekContest.DoubleWeek.NO36;

import java.util.*;

/**
 * @author zzj
 * @version 1.0
 * @date 2020/10/3 20:51
 */
public class Solution2 {
    public static void main(String[] args) {
        String str = "11:21";
        //System.out.println(str.split(":"));
        for (String temp : str.split(":")) {
            System.out.println(temp);
        }
    }

    public List<String> alertNames(String[] keyName, String[] keyTime) {
        Map<String, List<Integer>> personToTime = new HashMap<>();
        int length = keyName.length;
        for (int i = 0; i < length; i++) {
            List<Integer> tempLis = personToTime.getOrDefault(keyName[i], new ArrayList<>());
            //如果String的格式我们都已经确定了 就像这里00:00 那么我们可以直接转换成字符串 获取数字对应的下标即可
            String[] times = keyTime[i].split(":");
            tempLis.add(Integer.parseInt(times[0]) * 60 + Integer.parseInt(times[1]));
            personToTime.put(keyName[i], tempLis);
        }
        List<String> personName = new ArrayList<>();
        for (Map.Entry<String, List<Integer>> entry : personToTime.entrySet()) {
            if (entry.getValue().size() < 3) {
                continue;
            }
            List<Integer> timeList = entry.getValue();
            Collections.sort(timeList);
            int lastIndex = timeList.size() - 3;
            for (int i = 0; i <= lastIndex; i++) {
                if (timeList.get(i + 2) - timeList.get(i) <= 60) {
                    personName.add(entry.getKey());
                    break;
                }
            }
        }
        Collections.sort(personName, (o1, o2) -> {
            char[] chars1 = o1.toCharArray();
            char[] chars2 = o2.toCharArray();
            int i = 0;
            while (i < chars1.length && i < chars2.length) {
                if (chars1[i] > chars2[i]) {
                    return 1;
                } else if (chars1[i] < chars2[i]) {
                    return -1;
                } else {
                    i++;
                }
            }
            if (i == chars1.length) {  //o1到头
                return -1;
            }
            if (i == chars2.length) { //o2到头
                return 1;
            }
            return 0;
        });

        return personName;
    }
}
