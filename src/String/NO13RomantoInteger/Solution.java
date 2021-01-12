package String.NO13RomantoInteger;

public class Solution {

    public static void main(String[] args) {
        String str="0";
        System.out.println(Integer.valueOf(str));
    }

    public int[] map = null;

    {
        map = new int[128];
        map['I'] = 1;
        map['V'] = 5;
        map['X'] = 10;
        map['L'] = 50;
        map['C'] = 100;
        map['D'] = 500;
        map['M'] = 1000;
    }


    public int romanToInt(String s) {
        int res = 0;
        int length = s.length();
        if (length == 0) {
            return res;
        }
        int lastIndex = length - 1;
        int i = 0;
        for (; i < lastIndex; i++) {
            char ci = s.charAt(i);
            char cii = s.charAt(i + 1);
            if (map[cii] > map[ci]) {
                res += map[cii] - map[ci];
                i++;
            } else {
                res += map[ci];
            }
        }
        if (i == length) {
            return res;
        }
        return res += map[s.charAt(lastIndex)];
    }

}
