package String.NO1081SmallestSubsequenceofDistinctCharacters;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * @author zzj
 * @version 1.0
 * @date 2020/12/9 14:52
 * @description
 */
public class Solution {

    public String smallestSubsequence(String s) {
        int[] count = new int[123];
        boolean[] isVisit = new boolean[123];
        char[] chars = s.toCharArray();
        for (char c : chars) {
            count[c]++;
        }
        Deque<Character> stack = new ArrayDeque<>();
        for (char c : chars) {
            while (!isVisit[c] && !stack.isEmpty() && count[stack.peek()] > 0 && stack.peek() > c) {
                isVisit[stack.pop()] = false;
            }
            if (!isVisit[c]) {
                stack.push(c);
                isVisit[c] = true;
            }
            count[c]--;
        }
        StringBuilder res = new StringBuilder();
        while (!stack.isEmpty()) {
            res.append(stack.pollLast());
        }
        return res.toString();
    }

}
