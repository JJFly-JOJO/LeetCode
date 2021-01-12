package String.NO290WordPattern;

import java.util.HashMap;
import java.util.Map;

public class Solution {

    public static void main(String[] args) {
        String pattern = "";
        String str = "";
        System.out.println(new Solution().wordPattern(pattern, str));
    }

    public boolean wordPattern(String pattern, String str) {
        //分割str字符
        //String[] strs = str.split("\\s+");
        //注意二者效率是不一样的 后者效率高
        String[] strs = str.split(" ");
        int length = pattern.length();
        int lengthS = strs.length;
        if (length != lengthS) {
            return false;
        }
        int[] mapP = new int[26];
        int num = 1;
        Map<String, Integer> mapS = new HashMap<>();
        //!!!!!!!!!!!!!!!!!!!!!注意这里完全可以用i来代替累加的num 因为我们只需要保证不同的字符有不同的下标代替就可以了
        for (int i = 0; i < length; i++) {
            int index = pattern.charAt(i) - 'a';
            if (mapP[index] != mapS.getOrDefault(strs[i], 0)) {
                return false;
            } else {
                if (mapP[index] == 0) {
                    mapP[index] = num;
                    mapS.put(strs[i], num++);
                }
            }
        }
        return true;
    }

}
