package String.NO387FirstUniqueCharacterInaString;

import java.util.HashMap;

public class Solution {
    public int firstUniqChar(String s) {
        //利用hashmap 将每个字符作为key 数量作为value存入 然后遍历字符串取出第一个value为1的字符
        HashMap<Character, Integer> count = new HashMap<>();
        int n = s.length();
        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);
            count.put(c, count.getOrDefault(c, 0) + 1);
        }
        for (int i = 0; i < n; i++) {
            if (count.get(s.charAt(i)) == 1)
                return i;
        }
        return -1;
    }
}
