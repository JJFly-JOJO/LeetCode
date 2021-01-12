package String.NO358RearrangeStringkDistanceApart;

import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Stack;

/**
 * @author zzj
 * @version 1.0
 * @date 2020/11/9 11:31
 * @description
 */
public class Solution {

    public String rearrangeString(String s, int k) {
        int len = s.length();
        if (len == 1) {
            return s;
        }
        char[] chars = s.toCharArray();
        int[] mapCount = new int[123];
        int[] mapIndex = new int[123];
        //initialize
        Arrays.fill(mapIndex, -len);
        for (char c : chars) {
            mapCount[c]++;
        }
        //queue int o[2] 第一项o[0]记录字母对应ascii 第二项o[1]记录字母数量
        Queue<int[]> q = new PriorityQueue<>(26, (o1, o2) -> {
            //dict sort
            return o1[1] == o2[1] ? o1[0] - o2[0] : o2[1] - o1[1];
        });
        for (int i = 61; i < 123; i++) {
            if (mapCount[i] > 0) {
                q.add(new int[]{i, mapCount[i]});
            }
        }
        StringBuilder sb = new StringBuilder();
        Stack<int[]> st = new Stack<>();
        for (int i = 0; i < len; i++) {
            st.push(q.poll());
            while (i - mapIndex[st.peek()[0]] < k && !q.isEmpty()) {
                st.push(q.poll());
            }
            int[] t = st.pop();
            if (i - mapIndex[t[0]] < k) {
                return "";
            }
            sb.append((char) t[0]);
            mapIndex[t[0]] = i;
            t[1]--;
            if (t[1] > 0) {
                q.add(t);
            }
            while (!st.isEmpty()) {
                q.add(st.pop());
            }
        }
        return sb.toString();
    }
}
