package Tree.NO331VerifyPreorderSerializationofaBinaryTree;

import java.util.ArrayDeque;

/**
 * @author zzj
 * @version 1.0
 * @date 2021/5/21 14:34
 * @description
 */
public class Solution {

    public static void main(String[] args) {
        System.out.println(new Solution().isValidSerialization("9,#,92,#,#"));
    }

    public boolean isValidSerialization(String preorder) {
        char[] chars = preorder.toCharArray();
        ArrayDeque<Integer> stack = new ArrayDeque<>();
        stack.push(1);
        int idx = 0;
        while (idx < chars.length) {
            if (stack.isEmpty()) {
                return false;
            }
            if (chars[idx] == ',') {
                idx++;
                continue;
            }
            if (chars[idx] == '#') {
                int val = stack.pop() - 1;
                if (val > 0) {
                    stack.push(val);
                }
                idx++;
            } else {
                while (idx < chars.length && chars[idx] != ',') {
                    idx++;
                }
                int val = stack.pop() + 1;
                stack.push(val);
            }
        }
        return stack.isEmpty();
    }

}
