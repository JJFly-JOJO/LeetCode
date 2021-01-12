package String.NO14LongestCommonPrefix;

public class Solution {

    public static void main(String[] args) {
        String a = "abcd";
        System.out.println(a.substring(0, 2));
        String b = null;
        System.out.println(b);
        String c = "";
        System.out.println("--" + c + "--");
        //String d; //d没有初始化 报错 erro
        //System.out.println(d==null);

        int[] arrayInt1 = new int[0];
        System.out.println(arrayInt1);
        int[] arrayInt2 = null;
        System.out.println(arrayInt2);
    }

    public String longestCommonPrefix(String[] strs) {
        if (strs == null || strs.length == 0)
            return "";
        int length1 = strs[0].length();
        int count = strs.length;
        for (int i = 0; i < length1; i++) {
            for (int j = 1; j < count; j++) {
                if (i == strs[j].length() || strs[0].charAt(i) != strs[j].charAt(i)) {
                    return strs[0].substring(0, i);
                }
            }
        }
        return strs[0];//strs[0]被完全匹配
    }
}
