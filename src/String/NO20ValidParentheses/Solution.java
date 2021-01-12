package String.NO20ValidParentheses;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * @author zzj
 * @version 1.0
 * @date 2020/12/4 11:27
 * @description
 */
public class Solution {

    public boolean isValid(String s) {
        char[] chars = s.toCharArray();
        Map<Character, Character> map = new HashMap<>();
        map.put('(', ')');
        map.put('{', '}');
        map.put('[', ']');
        Stack<Character> stack = new Stack<>();
        for (char aChar : chars) {
            if (map.containsKey(aChar)) {
                stack.push(aChar);
            } else {
                if (stack.isEmpty() || map.get(stack.pop()) != aChar) {
                    return false;
                }
            }
        }
        return stack.isEmpty();
    }

}
